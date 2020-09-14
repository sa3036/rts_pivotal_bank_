# FROM centos:7.3.1611
# RUN yum install -y \
# java-1.8.0-openjdk \
# java-1.8.0-openjdk-devel
# ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk/

FROM maven:3.6.3-jdk-8
COPY ./. /
# Google Chrome

ARG CHROME_VERSION=83.0.4103.61-1
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable=$CHROME_VERSION \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

# ChromeDriver

ARG CHROME_DRIVER_VERSION=83.0.4103.39
RUN wget -q -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
    && unzip /tmp/chromedriver.zip -d /usr/bin/ \
    && rm /tmp/chromedriver.zip

CMD tail -f /dev/null