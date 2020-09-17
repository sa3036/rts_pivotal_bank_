## https://github.com/sa3036/rts_pivotal_bank_/compare/c6e22ea..60bf248

accounts-service/src/main/java/io/pivotal/accounts/AccountsApplication.java
accounts-service/src/main/java/io/pivotal/accounts/config/BeanConfiguration.java

---
portfolio-service/src/main/java/io/pivotal/portfolio/PortfolioApplication.java
portfolio-service/src/main/java/io/pivotal/portfolio/config/BeanConfiguration.java
portfolio-service/src/main/java/io/pivotal/portfolio/service/PortfolioService.java
portfolio-service/src/main/java/io/pivotal/portfolio/service/QuoteRemoteCallService.java
---

quotes-service/src/main/java/io/pivotal/quotes/QuotesApplication.java
quotes-service/src/main/java/io/pivotal/quotes/domain/Security.java

---

user-service/src/main/java/io/pivotal/user/UserApplication.java
user-service/src/main/java/io/pivotal/user/config/BeanConfiguration.java
---

web-ui/src/main/java/io/pivotal/web/WebApplication.java
web-ui/src/main/java/io/pivotal/web/config/BeanConfiguration.java
web-ui/src/main/java/io/pivotal/web/config/WebSecurityConfig.java
web-ui/src/main/java/io/pivotal/web/service/AccountService.java
web-ui/src/main/java/io/pivotal/web/service/AnalyticsService.java
web-ui/src/main/java/io/pivotal/web/service/PortfolioService.java
web-ui/src/main/java/io/pivotal/web/service/QuotesService.java
web-ui/src/main/java/io/pivotal/web/service/UserService.java

----
# Changes

delete
user-service/src/main/java/io/pivotal/user/config/WebConfig.java
user-service/src/main/java/io/pivotal/user/config/WebMvcConfig.java
quotes-service/src/main/java/io/pivotal/quotes/domain/AlphaAdvantageQuote.java
quotes-service/src/main/java/io/pivotal/quotes/domain/AlphaAdvantageQuoteList.java
quotes-service/src/main/java/io/pivotal/quotes/domain/AlphaAdvantageResponse.java
accounts-service/src/main/java/io/pivotal/accounts/config/WebConfig.java

checkout

quotes-service/src/main/java/io/pivotal/quotes/domain/IexBatchQuote.java
quotes-service/src/main/java/io/pivotal/quotes/domain/IexQuote.java
quotes-service/src/test/java/io/pivotal/quotes/configuration/TestConfiguration.java
quotes-service/src/test/java/io/pivotal/quotes/service/QuoteServiceTest.java
quotes-service/src/main/java/io/pivotal/quotes/domain/QuoteMapper.java
quotes-service/src/main/java/io/pivotal/quotes/service/QuoteService.java

accounts-service/src/test/java/io/pivotal/accounts/configuration/ServiceTestConfiguration.java
accounts-service/src/test/java/io/pivotal/accounts/controller/AccountsControllerTest.java

portfolio-service/src/test/java/io/pivotal/portfolio/config/ServiceTestConfiguration.java
portfolio-service/src/test/java/io/pivotal/portfolio/controller/PortfolioControllerTest.java
web-ui/src/main/java/io/pivotal/web/domain/User.java
