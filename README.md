# 简单配置管理服务

## 概述
本配置管理服务主要是解决一类配置参数的配置管理。那主要解决哪一类配置参数呢？举个例子，连接数据库，需要用户名，密码，url地址等参数，这类参数本配置服务不解决，原因是这类参数更改需要进行服务容器的重启才能整体生效。数据库连接参数，在容器启动时调用生成datasource，Connection实例，一但实例生成之后，就不再需要该参数。后续的代码只需要使用datasource,Connection实例即可。如果数据库的连接参数更改需要重建datasource,Connection 实例才能真正的使用参数变更有效。这个过程相当复杂，并伴随着服务容器的重启动作。所以本配置服务不解决这类参数。

那么剩余的另一类参数，本配置服务将去解决。即达到的目的，在外部参数更改，而不重启应用服务器，也能让参数在应用服务中即时生效的效果。这一类参数主要是开关参数，业务调用过和参数等

## 设计思路
### 消费端
在消费端，将所使用的参数，全部存储在ConcurrentHashMap中，使用时即通过业务键在ConcurrentHashMap中获取相应的值，并在业务逻辑中进行使用。当服务端参数发生变更时，通过消费端提供的接口，通知相应的消费端。ConcurrentHashMap作为消费端的参数暂存容器。这样可以减少远程调用的性能损耗以及服务器的容错能力。消费端在在容器启动时，初始化ConcurrentHashMap，导入相应的业务参数。

### 服务端

在服务端，提供的功能，即参数的CRUD、并当参数变化时提供参数变化的通知功能、查询所有参数初始化功能。为什么不将服务端直接作为参数的服务端，消费端通过rpc调用这种方式使用呢？原因在消费端中已说明了。

### 细节
远程 rpc调用使用dubbo；服务端参数变化通知消费端的功能使用dubbo的集群broadcast功能；


## 使用示例
### 客户端spring配置示例
		<dependency>
			<groupId>com.z.config</groupId>
			<artifactId>simple-config-api</artifactId>
			<version>1.1.1-SNAPSHOT</version>
		</dependency>
		
			
	<bean id="localConfigService" class="com.z.config.service.support.LocalConfigServiceImpl"   />
	
	<bean id="refreshLocalConfigListener" class="com.z.config.service.support.RefreshLocalConfigListenerImpl">
	<propertye name="localConfigService" ref="localConfigService" />
	<property name="configSrcSerivce" ref="configSrcService"/>
	</bean>
	
	
	<dubbo:reference id="configSrcService" interface="com.z.config.service.ConfigSrcService"/>
	<dubbo:service interface="com.z.config.service.RefreshLocalConfigListener" 
	ref="refreshLocalConfigListener"/>
	
	
	 客户端的业务代码直接使用localConfigService实例进行相应的参数获取即可;
### 服务端spring配置示例

	
	<bean id="configSrcService" class="com.z.config.server.service.support.ConfigSrcServiceImpl">
	<property name="configDao" ref="configDao"/>
	<property name="refreshLocalConfigListener" ref="refreshLocalConfigListener" />
	</bean>
	
	<dubbo:service interface="com.z.config.service.ConfigSrcService"  
	                 ref="configSrcService"/>
	

	
	<dubbo:reference id="refreshLocalConfigListener" class="com.z.config.service.RefreshLocalConfigListener" 
	            check="false" cluster="broadcast"/>
	
	



