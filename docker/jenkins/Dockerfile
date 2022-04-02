FROM jenkins/jenkins:lts
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY job.groovy /usr/local/job.groovy
COPY jenkins.yml /usr/local/jenkins.yml
COPY agent /usr/local/agent
ENV CASC_JENKINS_CONFIG /usr/local/jenkins.yml
USER root
RUN apt-get update && apt-get install -y build-essential && rm -rf /var/lib/apt/lists/*
USER ${user}

# install jdk 17
WORKDIR java-install
RUN apt-get update && apt-get install wget
RUN wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
RUN apt-get install -y ./jdk-17_linux-x64_bin.deb
WORKDIR /
RUN rm -rf java-install