#Configuring JAAS in Vaadin.

* Configuring the same wouldn't have been possible without prior work of mstahv [Author of repository](https://github.com/mstahv/vaadin-cdi-jaas-jbossas-example/tree/workaround)
* CSS for login pages are taken from [Quicktickets Dashboard](https://github.com/vaadin/dashboard-demo).
* Have tested this example on <b>Wildfly 8.2 Final</b>

JBoss/Wildfly has 'ApplicationRealm' configured in standalone.xml as default realm. 
Create an User for role users using add-user.[sh|bat]. If you have created a custom realm please make sure to configure the same in <b>web.xml</b>

Please feel free to contact me in case of any issues while running the samples..


