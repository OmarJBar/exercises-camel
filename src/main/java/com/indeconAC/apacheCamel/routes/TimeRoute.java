package com.indeconAC.apacheCamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.indeconAC.apacheCamel.models.Products;

/**
 * TimeRoute
 */
@Component
public class TimeRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
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

        // from("direct:product-list")
        from("timer:timer-test?period=3000")
        .log("${body}")
        .to("rest://get:/listar?host=localhost:8001")
        .log("${body}")
        .choice()
            .when(simple("${headers.CamelHttpResponseCode} contains '200'"))
                .log("Respuesta tipo: 200")
                .unmarshal()
                .json(JsonLibrary.Jackson, Products.class) //transform
                .split(body())
                    .log("producto : ${body.cobre.toString}")
                    .endChoice()
            .otherwise()
                .to("direct:log-primary", "direct:log-secondary")
        
        // .unmarshal()
        // .json(JsonLibrary.Jackson, Products.class)
        .end();
        //#endregion

        from("direct:log-primary")
        .log("mensaje 1, ${body}");

        from("direct:log-secondary")
        .log("mensaje 2 ${body}");

    }


}