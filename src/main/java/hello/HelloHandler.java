package hello;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class HelloHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {

        context.response()
                .putHeader("Content-Type", "text/plain; charset=utf-8")
                .end("Hello from Vert.X!");
    }

}
