package com.indeconAC.apacheCamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.indeconAC.apacheCamel.models.Products;
import com.indeconAC.apacheCamel.models.Values;

/**
 * TimeRoute
 */
@Component
public class TimeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        errorHandler(deadLetterChannel("direct:error-log"));

        from("direct:error-log")
        .log("${body}")
        .transform()
        .constant("Ha ocurrido un error en este punto")
        .log("${body}");

        // cola / queue
        // tranform
        // database / o finalidad

        // #region basic timer
        // from("timer:timer-test?period=3000")  //enpoint
        // .log("${body}")
        // .transform()
        // .constant("Messaje ")
        // .log("${body}");
        //#endregion

        //#region example files

        // from("file:files/input")
        // .log("${headers}")
        // .to("direct:someName")
        // .log("FileName: ${headers.CamelFileName}")
        // .to("file:files/ouput");

        // from("direct:someName")
        // .log("mensaje 2, ${headers.CamelFileLastModified}");

        //#endregion

        //#region

        from("direct:product-list")
        // from("timer:timer-test?period=10000")
        // .log("${body}")
        .wireTap("direct:call-list-products")
        .wireTap("direct:call-find-products")

        .end();
        //#endregion

        from("direct:log-primary")
        .log("se redirigio a log 1, ${body}");

        from("direct:log-secondary")
        .log("mensaje 2 ${body}");

        //#region listar productos
        from("direct:call-list-products")
        .transform()
        .constant("Before to get call Rest all products")
        .to("rest://get:{{"+Variables.CONF_PROD_SERVICE_URI+"}}?host={{"+Variables.CONF_PROD_SERVICE_HOST+"}}")
        .log("${body}")
        .choice()
            .when(simple("${headers.CamelHttpResponseCode} contains '200'"))
                .log("Respuesta tipo: 200")
                .unmarshal()
                .json(JsonLibrary.Jackson, Products.class) //transform
                .split(body())
                    .log("producto : ${body.cobre.key}")
                    .endChoice()
            .otherwise()
                .to("direct:log-primary")
        .end();
        //#endregion

        from("direct:call-find-products")
        .delay(2000)
        .transform()
        .constant("Before to get call Rest find products")
        .recipientList(simple("rest://get:{{config.client.product-service.uriValues}}${headers.key}?host={{"+Variables.CONF_PROD_SERVICE_HOST+"}}"))
        .choice()
            .when(simple("${headers.CamelHttpResponseCode} contains '200'"))
                .log("Respuesta tipo: 200")
                .log("${headers.key}")
                .unmarshal()
                .json(JsonLibrary.Jackson, Values.class) //transform
                .split(body())
                    .log("producto : ${body.values}")
                    .endChoice()
            .otherwise()
                .to("direct:log-primary");
    }
}