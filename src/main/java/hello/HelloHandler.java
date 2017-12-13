package hello;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class HelloHandler implements Handler<RoutingContext> {

    private final TemplateEngine engine = FreeMarkerTemplateEngine.create();

    @Override
    public void handle(RoutingContext context) {

        // set variable
        context.put("greeting", "Hello using FreeMarker on Vert.x!");

        engine.render(context, "templates/", "hello.ftl", res -> {
            if (res.succeeded()) {
                context.response()
                        .end(res.result());
            } else {
                context.fail(res.cause());
            }
        });
    }

}
