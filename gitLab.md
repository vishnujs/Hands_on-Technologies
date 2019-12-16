/**git lab installation guide for ubuntu machines**/
```
$:apt-get install openssh-server postfix -y
	there will be a gui
		set the internet option with the ip-address as you wish
		www.tkmcscedepartment.com
```
get the latest package from the gitlab package repository
	on selecting it we can get the 
```
$:dpkg -i gitlab***

nano /etc/gitlab/gitlab.rb
```
change the "external_url" field with the hosted address in openssh
```
git-ctl reconfigure
```
check the ip that was configured for git lab should be able to get a page on gitlab
set a new password for the root user
login with user as "root" and with the gieven password

//need to explore into how to configure the port
//how to make gitlab hosted in a laptop access all of these things

its also to be noted that this is a self-hosted one with a liscence for the organisation

for those who want to start a startup and all "bitbucket" is one of the most widely used such git repository along with "jira". upto five users can use that repo
