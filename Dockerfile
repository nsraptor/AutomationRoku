# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the POM file to the container
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the source code to the container
COPY src src

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y wget gnupg unzip && \
    rm -rf /var/lib/apt/lists/*

## Download and install Chromium
#RUN apt-get update && \
#    apt-get install -y chromium \
#
## Install necessary dependencies, including GnuPG
#RUN apt-get update && \
#    apt-get install -y wget unzip gnupg2 && \
#    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-archive-keyring.gpg && \
#    echo "deb [signed-by=/usr/share/keyrings/google-archive-keyring.gpg] http://dl.google.com/linux/chrome/deb/ stable main" | tee /etc/apt/sources.list.d/google-chrome.list > /dev/null && \
#    apt-get update && \
#    apt-get install -y google-chrome-stable && \
#    CHROME_DRIVER_VERSION=$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
#    unzip chromedriver_linux64.zip && \
#    rm chromedriver_linux64.zip && \
#    mv chromedriver /usr/local/bin/ && \
#    apt-get remove -y wget unzip && \
#    apt-get clean && \
#    rm -rf /var/lib/apt/lists/*
#
#RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm \
#    && yum install -y ./google-chrome-stable_current_x86_64.rpm \
#    && rm -f ./google-chrome-stable_current_x86_64.rpm

# Run the Maven build to compile and package the project
RUN mvn clean install

# Expose any necessary ports for Selenium WebDriver or your application
EXPOSE 8080

# Command to run your Selenium tests via Maven
CMD ["mvn", "clean", "test"]