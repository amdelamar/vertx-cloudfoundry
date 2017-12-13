package hello;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    public static int HTTPPORT = 8080;
    public static final String VERSION = "3.5.0";

    private static Vertx vertx;
    private static HttpServer httpServer;
    private static Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    public static void main(String[] args) {
        // Vertx core
        vertx = Vertx.vertx();

        // Deploy Verticle
        vertx.deployVerticle(new MainVerticle(), res -> {
            if (!res.succeeded()) {
                logger.error("FATAL: Deploy Verticle failed!");
            }
        });
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);

        // Check if necessary files are valid
        if (isValid().failed()) {
            getVertx().close();
            System.exit(1);
        }

        // Create HTTP server
        httpServer = getVertx().createHttpServer(new HttpServerOptions().setLogActivity(true));

        // Map Routes
        Router mainRouter = Router.router(getVertx());
        mainRouter.get()
                .path("/hello")
                .handler(new HelloHandler());

        // Webroot resources
        mainRouter.route("/*")
                .handler(StaticHandler.create());

        // Templating
        mainRouter.get()
                .path("/template")
                .handler(new TemplateHandler());

        // Add Subrouter api
        Router apiRouter = Router.router(getVertx());
        apiRouter.get()
                .path("/")
                .handler(new ApiHandler());
        mainRouter.mountSubRouter("/api", apiRouter);

        // Set Router
        httpServer.requestHandler(mainRouter::accept);

        // Start listening
        httpServer.listen(HTTPPORT, handler -> {
            if (handler.succeeded()) {
                logger.info("Listening on port: " + HTTPPORT);
            } else {
                logger.error("Failed to bind on port " + HTTPPORT + ". Is it being used?");
            }
        });
    }

    /**
     * Check if this application has access to the necessary files
     * and resources it needs. Like keystore and webroot.
     * @return Future (use Future.failed() to check if false)
     */
    private Future<Void> isValid() {
        Future<Void> future = Future.future();

        boolean flag = true;

        // PORT env check
        try {
            int port = Integer.parseInt(System.getenv("PORT"));
            HTTPPORT = port;
        } catch (Exception e) {
            logger.warn("Environment variable PORT not found. Defautling to: " + HTTPPORT);
        }

        File webroot = new File(MainVerticle.class.getResource("/webroot")
                .getPath());
        if (!webroot.exists() || !webroot.isDirectory()) {
            logger.error("/webroot/ not found or can't read. Expected it here: " + webroot.getAbsolutePath());
            flag = false;
        }

        if (flag) {
            future.complete();
        } else {
            future.fail("App invalid config.");
        }

        return future;
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        super.stop(stopFuture);
        logger.info("Stopped listening on port: " + HTTPPORT);
    }
}
