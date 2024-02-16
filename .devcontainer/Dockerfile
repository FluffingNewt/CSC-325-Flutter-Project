from mcr.microsoft.com/devcontainers/base:ubuntu

ENV USER="vscode"

ENV LANG en_US.UTF-8
ENV DEBIAN_FRONTEND noninteractive

#####################################################################################

ENV HOME="/home/vscode"
ENV FLUTTER_HOME="${HOME}/sdk/flutter"
ENV ANDROID_HOME="${HOME}/sdk/android"

ENV PATH=${PATH}:${FLUTTER_HOME}/bin:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/emulator:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/platforms

# Make sure all required packages are installed
RUN apt update \
    && apt install -y \
      sudo \
      openjdk-17-jdk-headless \
      wget \
      gnupg \
      curl \
      git \
      clang \
      cmake \
      ninja-build \
      libgtk-3-dev \
      pkg-config \
      xz-utils \
      zip \
      unzip --no-install-recommends

# Clean Up
RUN apt-get autoremove -y \
    && apt-get clean -y \
    && rm -rf /var/lib/apt/lists/* 

# create user
RUN echo $USER ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USER \
  && chmod 0440 /etc/sudoers.d/$USER \
  && su ${USER}

USER $USER
WORKDIR /home/$USER

#####################################################################################

# Flutter
RUN mkdir "${HOME}/sdk" \
    && git clone -b master https://github.com/flutter/flutter.git ${FLUTTER_HOME} \
    && flutter --disable-analytics \
    && flutter config --enable-web \
    && flutter doctor

#####################################################################################

# Android SDK
ENV ANDROID_VERSION="34"
ENV ANDROID_BUILD_TOOLS_VERSION="34.0.0"
ENV ANDROID_ARCHITECTURE="x86_64"

RUN mkdir ${ANDROID_HOME} \
  && touch ${ANDROID_HOME}/repositories.cfg \
  && curl -o android_tools.zip https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip \
  && unzip -qq -d "${ANDROID_HOME}" android_tools.zip \
  && rm android_tools.zip \
  && mkdir ${ANDROID_HOME}/cmdline-tools/latest \
  && mv ${ANDROID_HOME}/cmdline-tools/bin ${ANDROID_HOME}/cmdline-tools/latest \
  && mv ${ANDROID_HOME}/cmdline-tools/lib ${ANDROID_HOME}/cmdline-tools/latest \
  && mv ${ANDROID_HOME}/cmdline-tools/NOTICE.txt ${ANDROID_HOME}/cmdline-tools/latest \
  && mv ${ANDROID_HOME}/cmdline-tools/source.properties ${ANDROID_HOME}/cmdline-tools/latest \
  && yes "y" | sdkmanager "build-tools;$ANDROID_BUILD_TOOLS_VERSION" \
  && yes "y" | sdkmanager "platforms;android-$ANDROID_VERSION" \
  && yes "y" | sdkmanager "platform-tools" \
  && yes "y" | sdkmanager "emulator" \
  && yes "y" | sdkmanager "system-images;android-$ANDROID_VERSION;google_apis_playstore;$ANDROID_ARCHITECTURE" \
  && yes "y" | sdkmanager --licenses

#####################################################################################