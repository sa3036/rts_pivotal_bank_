package io.pivotal.analytics.configuration;

import io.pivotal.analytics.entity.Trade;
import io.pivotal.analytics.repository.TradeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@EnableBinding(Sink.class)
public class SinkConfiguration {
    private static final Log logger = LogFactory.getLog(SinkConfiguration.class);

    @Autowired
    TradeRepository tradeRepository;

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void loggerSink(LinkedCaseInsensitiveMap payload) {

        // sample input message {orderid=32772, accountid=1, completiondate=2018-06-25 18:37:00.0, currency=USD, orderfee=10.50, ordertype=0, price=24.80, quantity=15, symbol=PVTL, userid=nfoles, tag=null}

        logger.info("found order: " + payload.toString());

        Trade trade = new Trade();

        if(payload.get("orderid") != null){
            trade.setOrderid(Integer.parseInt(payload.get("orderid").toString()));
        }

        if(payload.get("accountid") != null){
            trade.setAccountid(Integer.parseInt(payload.get("accountid").toString()));
        }


        if(payload.get("completiondate") != null){
            try {
                trade.setCompletiondate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(payload.get("completiondate").toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(payload.get("currency") != null){
            trade.setCurrency(payload.get("currency").toString());
        }

        if(payload.get("orderfee") != null){
            trade.setOrderfee(BigDecimal.valueOf(Double.parseDouble(payload.get("orderfee").toString())));
        }

        if(payload.get("price") != null){
            trade.setPrice(BigDecimal.valueOf(Double.parseDouble(payload.get("price").toString())));
        }

        if(payload.get("symbol") != null){
            trade.setSymbol(payload.get("symbol").toString());
        }


        if(payload.get("quantity") != null){
            trade.setQuantity(Integer.parseInt(payload.get("quantity").toString()));
        }

        if(payload.get("userid") != null){
            trade.setUserid(payload.get("userid").toString());
        }


        // date format; 2018-06-25 18:37:00.0

        tradeRepository.save(trade);
    }
}
