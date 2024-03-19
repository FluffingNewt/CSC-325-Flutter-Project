
# DevContainer Environment

## Introduction
A DevContainer, short for Development Container, is a pre-configured environment tailored for software development within a containerized environment. It encapsulates the dependencies, tools, and settings necessary for a consistent development experience across different machines. In the context of Flutter application development, where dependencies and configurations can be complex and prone to environment-specific issues, a DevContainer ensures that every team member works with the same development environment, eliminating any "__it works on my machine__" discrepancies.

## Configuration

### Base Image:
For Flutter application development, this container utilizes a Docker image specifically tailored for Flutter development. The base image includes the necessary SDKs, dependencies, and tools required for building and running Flutter applications.

### Additional Tools or Extensions:
In addition to the Flutter SDK, we have installed several additional tools and extensions within the DevContainer:

* __Git__: Version control system for managing the project's source code.
* __Dart and Flutter Extensions__: Extensions that provide syntax highlighting, code completion, and other features specific to Dart and Flutter development within Visual Studio Code.

### Configuration Settings:
To ensure compatibility and efficiency within the project, certain configuration settings within the DevContainer were setup. For example, the path was configured to include the Flutter SDK and various environment variables pertaining to user and SDK locations were created and set.

## VSCode Integration
In order to utilize the DevContainer, make sure the following requirements are met:

* __Docker__ is installed and running
* __VS Code__ is installed with the following extensions:
  * __Remote Development Extension Pack__

The DevContainer seamlessly integrates with Visual Studio Code (VS Code), allowing for a streamlined development experience. The Requirements above allow users to develop directly within the DevContainer environment while utilizing the features of VS Code. In addition, Dart and Flutter extensions for VS Code are installed and set up when building the DevContainer as described previously.

## Usage
Using the DevContainer for development is straightforward given that all previous requrements are met:
1. Clone the project repository (https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository?tool=webui).
2. Open the project directory in Visual Studio Code.
3. When prompted, click on "__Reopen in Container__" to open the project within the DevContainer environment.
4. Start coding! Edit your code as usual within VS Code.

### Creating and Running Flutter Applications:
To create a flutter app, enter the following command in the devcontainer's terminal:

`flutter create [app name of your choosing]`

Now, to run your app, cd into the newly created app directory and enter the following command:

`flutter run -d web-server`

## Challenges and Solutions
While setting up the DevContainer, the following challenge was encountered:

* __Installing Correct Linux and Flutter Dependencies__:  
While setting up the DevContainer, determining which Linux and Flutter dependencies that were necessary was unclear. To address this, all packages listed on the official Flutter documentation were installed (https://docs.flutter.dev/get-started/install/linux). Additionally, the command `flutter doctor` was used to identify any missing packages. This ensured a complete setup without any unnecessary complexity.

## Conclusion
Utilizing a DevContainer for Flutter application development has provided numerous benefits. It ensures consistency across development environments, streamlines setup for new members or machines needing access, and mitigates environment-specific issues. The process of configuring and using the DevContainer has also deepened my understanding of containerized development environments and improved my overall development workflow.