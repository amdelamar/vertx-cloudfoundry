package hello;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ApiHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {
        
        context.request().path();
        
        JsonObject json = new JsonObject();
        json.put("vertx", "Welcome");
        json.put("version", MainVerticle.VERSION);
        
        context.response()
        .putHeader("Content-Type", "application/json; charset=utf-8")
        .end(json.encode());
    }

}
