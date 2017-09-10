package com.newlight77.demo.vertx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class VerticleLauncher extends AbstractVerticle {

    private static Logger LOGGER = LoggerFactory.getLogger(VerticleLauncher.class);

    @Override
    public void start(Future<Void> future) {

        JsonObject appConfig = VertxConfig.singleton().loadBeneathAppConfig(config()).appConfig();
        LOGGER.info("start appConfig : {}", appConfig);
        VertxOptions vertxOptions = new VertxOptions(appConfig.getJsonObject("vertxOptions"));
        JsonArray verticlesConfig = (JsonArray) VertxConfig.singleton().verticlesConfig().getValue("verticles");

        Deployer.singleton().deploy(vertxOptions, verticlesConfig);

    }

    public static void main(String[] args) {
        new VerticleLauncher().start(Future.future());
    }

}
