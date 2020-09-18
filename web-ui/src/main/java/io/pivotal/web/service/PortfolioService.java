package io.pivotal.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import io.pivotal.web.domain.CompanyInfo;
import io.pivotal.web.domain.Order;
import io.pivotal.web.domain.Portfolio;
import io.pivotal.web.domain.Quote;
import io.pivotal.web.exception.OrderNotSavedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
@RefreshScope
public class PortfolioService {
	private static final Logger logger = LoggerFactory
			.getLogger(PortfolioService.class);
	@Autowired
	// @LoadBalanced
	private RestTemplate restTemplate;

    @Value("${pivotal.portfolioService.name}")
	private String portfolioService;

	@Value("${pivotal.downstream-protocol:http}")
	protected String downstreamProtocol;

	public Order sendOrder(Order order ) throws OrderNotSavedException{
		logger.debug("send order: " + order);
		
		//check result of http request to ensure its ok.
		
		ResponseEntity<Order>  result = restTemplate.postForEntity(downstreamProtocol + "://" + portfolioService + "/portfolio", order, Order.class);
		if (result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new OrderNotSavedException("Could not save the order");
		}
		logger.debug("Order saved:: " + result.getBody());
		return result.getBody();
	}
	
	@HystrixCommand(fallbackMethod = "getPortfolioFallback")
	public Portfolio getPortfolio(String user) {
		Portfolio folio = restTemplate.getForObject(downstreamProtocol + "://" + portfolioService + "/portfolio/{accountid}", Portfolio.class, user);
		logger.debug("Portfolio received: " + folio);
		return folio;
	}
	
	private Portfolio getPortfolioFallback(String accountId) {
		logger.debug("Portfolio fallback");
		Portfolio folio = new Portfolio();
		folio.setAccountId(accountId);
		return folio;
	}

}
