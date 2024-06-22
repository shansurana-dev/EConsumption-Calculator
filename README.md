# EConsumption-Calculator
Calculates energy consumption by a single dimmer light
Steps:
1. Read and parse input messages.
2. Process the messages and save the dimmer values.
3. Calculate the energy consumption using the dimmer values.
4. Handle duplicate, lost, and out-of-order messages.
5. Write test cases.

Approach to Implement a pipeline:
![alt text](https://lucid.app/lucidspark/eea553d6-43c8-4e55-b3fe-88b25f415f93/edit?invitationId=inv_14218bc2-7e68-4a0a-8401-3ccd5071d317)

1. Create a single Java Class to create the tool.
2. Git webhook triggers CI on Jenkins.
Continuous Integration:
3. EC2 (Large) deployment instance.
4. Install Jenkins, Maven and SonarQube plugins.
5. Update Docker image.
Continuous Deployment:
6. Update manifests and upload to repo.
7. Configure ArgoCD to watch the manifest repo for update and deploy to Kubernetes.