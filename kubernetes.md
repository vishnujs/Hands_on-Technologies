Kubernetes is a cluster orchestration tool 
	automated the distribution and scheduling of application containers
	master nodes coordinated also called node process
		maintaining state
		scheduling
		scaling

	nodes are the workers that run the application
		should have tools for handling the container such as docker or rkt
		have a kubelet that is resposible for intercating with master
		it communicates through kubermeted a
		minikube is a lightweight kubernetes implementation that create a VM in the local machine


pod
label
replica sets

kubernetes cluster consits of two types of resouces:
	maseter- cordinates
	node-worker

kubernetes cluster should have mininum three nodes

kubectl version 
	for checking the health of the kubernetes
kubectl nodes
	lists nodes of a particular nodes of the kubernetes

