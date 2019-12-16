# CICD
prathidhwani

## Monoliths
## MicroServices
- modules do not interact with each other
- lightweight an HTTP resource API
- golang with concurrency in mind
- java in regard with security
- each service can be written in different language with respect to the thing it is dealing with
-a sophisticated extension of <b>Service Oriented Architecture</b>(microservices are much more reliable and scalable)
- AMART ENDPOINTS AND DUMB PIPES
- easy to collaborate on-with onboarding members
  - as code size are less compared to a 
- macking change to a module only reauired buildin, testing and deploying of that particular module
- efficient and targeted scaling


### MicroServices communication
- REST API
- AMQP(Adavanced Message Qqueuing Protocol)
- RPC(Remote Procedure Call)
    - it can intercat with rpc over http
- charecteristics
    - component based programming(need to explore)
    - componentization via services
    - Decentralized Data Management
        - solves too many file descriptors(linux explore)
    - simple routing
    - failure resistant
        - eventhough it removes the bad container if done the same scenario how to avoid such a system
    - evolutionary
    
- Cons
    -  duplication of efforts(writing deployent manifests for each and every service settingup data sources for each and every servie and mch more)
        - helm configuration
        - teraform - for 
    can solve this scneario
    - lot of communication with most of them useless

### Containerizing microservices with docker
- predocker days
    - codes used to run in local but not in production
    - atleast two environments
- docker
    - it handles containers
        - contianers contain the application
        - containers are built around shared infrastructure
        - hypervicers(vm)
    - container is not a virtual machines
        - one vm or baremetal machine
        - conatiner utilizes the host structure
        - container are stateless and immutable
            -  modification can only be done with a new container configuraiton bring the container down
    - idea of container is not new
        - linux introduced 
            linux containers share the kernel of th ehost operating system
        - container rely on tow Linux Kernel features, namely <b>namespcae</b> and <b>cgroups</b>
    - namespaces
      - in isolation processes from each other on a host
      - dangling resources to processes without any hierarchy thery have to be pruned
      - PID Namespcae: PID 1(init process) to perform amanagement tasks and PID 1 may have its children running as different processes
      - Networking Namespace: rin programs on anay port without having any conflict with others. Same ports can be reutilized without having conflicting
      - Mount Namespace:Mount and unmount filesystems to the container without affecting the host filesystem. 
      - IPC Namespace: For managing access to Inter-process communication resources
      - UTS Namescpace: For isolating (missed need to continue)
    - linux namespaces can be created using unshare system call
    - Parent of PID 1 is PID 0(the Kernel itself)
    the process having PID 1 when viewd from ourside thename scape has a diff ID
    -  zombie process if another process in the namespace dies before its children, the childre
        - host connection limit - for databases
        -    file descriptor in the case of kafka -reduce the total number of file descritors-flush those that arenot used
    - if PID 1 dies, all the other processes are forcefully terminated and the namespace is cleaned up.
    - CIDRS(class less internet domain blah blah)
    - sigend,sigkill
    -  by defaulr there are no registerd signal handlers for PID 1
        - PID 1 cannot handle sig commands
        - if PID1 dies container dies
        - different user set for docker also so PID1 is not a good
        - docker init is to remove the PID 1 from using it. who uses PID 1?
        - should entry point of the container become PID 1?
            - docker makes conatiner entypontas pid1
            - this canbe dangerous and lead to sme unexpected behavious
            - usally an application is not desingend and meant to be the inint process
            - signale handling?
            - zombies?
    
    - first sigterm goes into container sigkill will be used
    - sigterm will uncommitting the non saved portions and becomes stable.
    - offset 
        - whos is suitable candidate for init process
            - init proess
            -  systemd as init process
                - journel ctl(need to search for)
                -  systemd cerates a mount namespacae for the ocnatiner entypoint and starts it
                - Rkt uses this approach
                -  conaiter spawner?

    - cgroups
        - control Groups
            - oomkilled-out of memmory killed
        - manages resourcelimit(cpu ,mem,ios)
        can be used to limit access to peripheral devices
        -  to prioritize access to resources(say a type of container is always preferred) over the other when schelduling happens in a pressurized enviornment)
        - monitoring resources
        - hierarchical grouping of resources(subgroups inherit resources tfrom parents)
    - Berkeley Packet Filter(BPF)
        -  a linux kernel system call
        - whatever system call is issued it can trace it.
        - by using sufficient low level programming language like c,c++ it can filter system calls
        - it filters network system calls
    -monitoring tools
        - they are using BEF and EBEF
        - it used to inject its own code into the code(new relics - used to inject this)-it was pretty slow
        - the BEF it just listens the system calls
            - it is more of a kind of realtime

    - vtx-enabled git, hyperviceer
    linux based containers will be created in the vtx of windows from that the docker works on the same-the same technique is used by mac also.

    - it is a client service architecture
    - dockerd-docker deamon 

    - three main components:
        -docker server/deamon:
        -docker host
        -docker registry

    - chrone job:a scheduled job
```
    dcoker build/pull/run will 
    
    send command to docker host-where docker deamon will take images and they are started as containers
```
- Docker images
    - bundled application package is called a Dockerimage
    - docker images can eber versioned using tags

    - docker registry
        - maintains docker repsitories(basically folders for string application)
-types of socker
    short lived application

    add to suders
    ```
    sudo usermod-aG docker <userName>
    ```
    docker verison check
    ```
    docker version
    ```
    for docker image list and container list
    ```
    docker images
    docker container ls
    ```
    start container
    ```
    docker run <conatiner-name>
    ```
    if no tags are proved it will take latest tagged
    docker conatiner run --detach --publish 8081:80 nginx:alpine
    
    third type is interactive mode
    ```
    docker exec --interactive --tty <hash-id> sh/bash
    ```
    sh or bash can be used

    after getting into the container you can check the ip with
    ```
    ps aux
    ```

### Containerizing an application

- steps
    - create an application
    - write Dockerfile
    - build the image
    - push the image to repository(optional)
    - run the image
- use comma for seperating commands for CMD in docker file

building docker filr
```
docker build -t <app name>:<version> .
```

from the information step1 will be pulling a python image which will contain multiple layers

- while building the images that are built previously will be 


kvm(not a continer resource),
lxm,rocket(rkt),bsd,zones and jales
differences with docker and rocket


### Dockerfile Instructions
- FROM:the base image
- LABEL:labels for organizing images in a project
- RUN: for executing shell instruction
- CMD:For running the software conatined by the container along with args
- ENTRYPOINT:specifies the main command to be run by the container
- VOLUME: Data volumes to be exposed are specifies here

### Dockerfile Instructions
- data can ebe persisted across container restarts
- Volumes are like virtual disk srives inside a conatiner

### Docker Networking
- virtula networking layer which helps the containers to communicate with each other.
- bridge network -used by deaults -gives coaniner its own ip address
- overlay network
- host network

### Orchestrating Docker Containers with kubernetes

#### Need for Orchestration System
- docker is fine for packaging and distributing containerized application
- cons
    - how can containers be coordinated
    - how to schedule them
    - is ther something like Zero-Downtime
    - how to monitor teh health of application

### Kubernetes

- De Facto Container Orchestration System
- maintained by CNCF
- mesos is also famous but kubernetes is by far the best
- chekc cncf cloud native

- A distributed system
- supports automatic rollbacks that are atomic
- auto-scaling of resources

- it is like a self healing
    - controller ->system->sensor back to contrller

architecture
    master node
    worker node(group)
    kubectl for interacting with the master

master component

- kue-apiserver
    - central management entity
    - central governing
    - receive all REST requests for modification
    -  validatied and configures data for api objects like pods,services replciation contrllers and other

- etcd cluster
    - distributed key-value store for storing the kubernets cluser data,api and service discover details
    - persistent
    - kube-apiserver can only access etcd clister
    - notifier cluster abur configration
    - high availablility and redundancy can be achieved by running multi-node etcd cluster(5 nides for production)
        - it is always we put odd numbers on case of state management it is stable. If one goes down then two nodes needs to be replication and for syncing.so when the node that went down will fail over(3 or 5 is prescrbed more thna 5 is useless)
- kube-controller manager
    - mangement engine of kuberneted
    -  run numerous controller porcess  in background(replication contrllers conrtol)
    -  watches out
- cloud-contrller-manager
    - applicable in case Kubernets is eing run and managed by some cloud provider.
- kube-scheduler
    -  schedules resources across workernodes based on resoure requirements and constraints
    - container is called pod(smallest deployable unit)
    - interacts with and updates kube-apiserver about scheduling status of resouce
- wokercomponents
    -  kubelet(heart of worker nodes)
    - kube-proxy(DNS resoluting and network recoverability)
- kubelet
    - primary service runnnign on a worker node
    - watches for changes in resourse specifiactions by interacting with kue-apiserver
    - ensures that resources are healty and in desired state.
    - reports about health of the node/host to the master
    - interacts with kube-apiserver and downloads secrets/configmaps as required duting scheduling of pods.
- kube-proxy
    - Proxy service running on each worker nodes

- Some kubernetes concepts
    -  pods
    - services
    - volumes
    - namespaces

- pods(pod of whales)
    - docker is on shipping on container on ship
    - kubernets the one who controls based on the aquatic
    - smallest deployable units of computing that canbe created and managed in kubernetes
        - side-car pattern:where loadbalancer is being relied on by an application
    - group of one or more container that should be contrlled as a single applicaiton
    - container inside a pod share the same storage and network
    - conatiner sinside a pods share same storage and n/w
    -  contents of a pod are oloacfted cos scheduleds and run in a shared context
        - readiness and liveliness
- Services
    -  pods are volatile/mortal(a pod may die)
    - a gateway which represents and addresses a logical set of pods.
    - upscaling and downscaling can be configured
    - exposes an application as a network service
    - allocates a single 
-Types of Volumes
    - file sharing volumes like NFS
    - Node-local volumes like emtyDir and hostPath  
    - Cloud-provider specific like Elastic Block Store and Persistent Disk

- Namespaces
    - a virtula cluster inteded for env with multiple users spread across multiple teams or projects

        
```
sudo minikube status
```
--vm-driver=none to be done without vms(dont use i)
```
sudo minikube start --vm-driver=none
```
```

sudo kubectl applu -f nginx/depoyment.yaml
kubectlm get pods -n default
```

```
vim cornpb/cronjpb.yaml
sudo kubectl apply -f cronjob/cronjob.yaml
sudo kubectl get cronobs
sudo kubectl apply -f pod.yaml
sudo kubectl apply -f configmap.yamls
sudo kubectl get all

```
delet all
```
sudo kubectl delete deployment
```
if the file is known then go to that location and 
```
delete -f
```

- types of deploment
    - blue-green deployentp(more of a kind of monolithic-not usually used in kube)
    - canary testing
    - rolling

- types of ports
    - node ports
    - load balancer

### Templotizing
- Helm    
    - a package manager for kubernets
    - organzes kubernetes objects in form of a package(chart) that can be downloaded and installed in just on click
    - powerful template engine for Kubernetes manifests
    - capable of doing Kubernetes release efficiently
    - used for upgrading,and release


    ```

    ```
- Need for Helm Templating
    - wiritng YAMLS multiple times if overwhelming
    - preparing too many YAML maifests by hand is error-prone and monotonous
    - manual preparation of kubernetes manifests is not scalable ad is hard
    - singlw application may comprise of multiple interdependent kubernets resouces each requiring its own detailed manifest files
- Key concepts of Helm
    - Charts: pack
    - Release
    - Repository

```
sudo helm create <application>
```
tiller(need research)
helm is both running as client and server


- pre-Iac Days
    - manually constructed infrastructure
    - hand-edited configuration files
    - automated,repeatable andn reusable is much harder
- Infrastructure as code
    - enables to describe infrastructure and application using source code
    - versionc ontrlled source code
    - easy rollback
- whats infrastructure
    - clouse services
    - hosts,VMs
- Terraform
    - its a declarative form of coding
    - utilizes graph based approach to figure our resourse dependecy
    - import existin resources and get started
    - provisioning tool and partially a confguration tool
- Main commands
    - plan
    - appply 
    - destroy

- Terraform files
    - HCL(HashCorp Configuraiton Language)
    - configurationfielsuse .tf or .tf.jsone xtension
- Providers
    - connects terraform to the infrastrucure that one wants to manage
    - poplar providers
        - AWS
        - GCP
        - Azure
- Resources
    - actula infarastrucure components that one wants ot mange
- Terraform State
    terraform applu
    terraform.tf.state.backup : should be secure and should be added to .gitignore
- ts file should be kept in 
- hudsons,pinnacle, jenkins
- Taints
    - plans may fail
    - resources arenot rollbed-back if during execution 
#### Visualizing Terraform DAG
    - Terraform builds a DAG for ersources
    - "terraform graph" outputs a DOT graph
    - Terraform Modiles

#### jenkins
    - enkins file that can be created instead of pipeline
    -configure
        - git ops
        - pat(personalized access tag)




#
### Doubts
- datalayer spread across
    - on order-how integrity is maintained
    -  data engineering pipelines
        - presto,hadoop spark and all
        -  it will query multiple datalayer
        - if isolation is empahsised-presto allows the same
    - regular rdbms 
- data duplication
    - request and limit on each container will handle this data duplication and data synching without affecting the system
- witis datastore specifically for microservices
- log aggragation and log sweeping(not sure)
    - elastic stack search
    - log layer then sweeper layer
- deamon sets-back ground processes that controls the number of instances-log stash running on it.if file beat stops then this can be pushed to elastic search and view in kibana(tool for visualisation)
- mongobd as datasource-document db,bson,keyvalue store, for javascript
-asset properties,base property- Availability, key consistency-CAP(consistency availability and parition tolerance-split brince and broom(brain functionality))-lamports law/clock
    - replciation factor of 5 (one master)
    - active passive
    - writes on master
    - reads on slaves
- graphana,zensu(spelling not sure) tools used for monitoring
- p99, p95, p90 percentiles


```
message queuing systems
ZeroMq(need to find more on that)

use case of simple queuing is that of the multiple hit on it would process generating multiple otps.it happended due to slow queuing service.


Application monitoring tools
    latency on the application
    queuing
    no of function calls
    #prometheus
Data monitoring tools

freeplane - for mindmapping

check grep -l

check ps aux

docker dive(wagoodman:dive)

tree
```
- types of cloud deployment
  - on-dempand(reserved instance RIs)
  - spot instance

- effumerable volume
    - like a volative memory

- volumes


- kops vs kubeadm
- minikube

- chekc watch command

-yaml templating tool by IBM



github.com/greatdeavks
greatdevaks@gmail.com
greatdevaks
