# EConsumption-Calculator
Calculates energy consumption by a single dimmer light
Steps:
1. Read and parse input messages.
2. Process the messages and save the dimmer values.
3. Calculate the energy consumption using the dimmer values.
4. Handle duplicate, lost, and out-of-order messages.
5. Write test cases.

Approach to Implement a pipeline:
![Capture](https://github.com/shansurana-dev/EConsumption-Calculator/assets/83782547/bcc2ad59-7ee0-4978-962d-a8cf60e20617)

1. Create a single Java Class to create the tool.
2. Git webhook triggers CI on Jenkins.
Continuous Integration:
3. EC2 (Large) deployment instance.
4. Install Jenkins, Maven and SonarQube plugins.
5. Update Docker image.
Continuous Deployment:
6. Update manifests and upload to repo.
7. Configure ArgoCD to watch the manifest repo for update and deploy to Kubernetes.

# Simple steps to run the tool on EC2 instance using Docker:
1. open AWS CLI or connect from the terminal to the EC2 instance.
2. sudo apt update -y
3. sudo apt install docker.io -y
4. sudo systemctl status docker
5. In order to give permission to connect to docker daemon, run the following command:
sudo usermod -aG docker ubuntu
logout and login again to the EC2 instance.
docker run hello-world
6. Clone the git repository
7. Run the docker build and run commands to start the app
