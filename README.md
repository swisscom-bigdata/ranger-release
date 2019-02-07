# Ranger Knox Plugin for HDP 2.6.4.91-3 and Apache Knox 1.2.0

This branch should only be used to build the Ranger Knox Plugin for Apache Knox 1.2.0 working with the Ranger version in HDP 2.6.4.91-3.

## Build instructions
Find below the instruction on how to build the plugin. It requires to build a number of dependencies as they are not available in any public Maven repository (or not in any repo I tried and have access to at least).

### Prerequisites:
* mvnvm (Maven Version Manager)
* Ant (tested with version `1.10.5`)
* Graddle (tested with version `4.10.2`)
* Java 8 (tested with version `1.8.0_181`)

Make sure you have the following Maven repositories in your `~/.m2/settings.xml`:

```
<repository>
  <snapshots />
  <id>spring-plugins</id>
  <name>spring-plugins</name>
  <url>http://repo.spring.io/plugins-release/</url>
</repository>
<repository>
  <snapshots />
  <id>hortonworks</id>
  <name>hortonworks</name>
  <url>http://repo.hortonworks.com/content/repositories/releases/</url>
</repository>
```


### Step by step instructions
```
cd /tmp
mkdir ranger_plugin
cd ranger_plugin

git clone https://github.com/hortonworks/zookeeper-release.git
cd zookeeper-release
git checkout tags/HDP-2.6.4.91-3-tag
sed -i -- 's/3.4.6/3.4.6.2.6.3.0-SNAPSHOT/g' build.xml
brew install automake
ACLOCAL="aclocal -I /usr/local/share/aclocal" autoreconf -if
ACLOCAL_PATH=/usr/local/share/aclocal autoreconf -if
ACLOCAL_FLAGS="-I /usr/local/share/aclocal" autoreconf -if
ant mvn-install
# Yes, it works on second attempt...!?
ant mvn-install
cd ..

git clone https://github.com/hortonworks/hadoop-release.git
cd hadoop-release
git checkout tags/HDP-2.6.4.91-3-tag
brew install protobuf@2.5
mvn -DskipTests=true clean compile package install
cd ..

git clone https://github.com/hortonworks/kafka-release.git
cd kafka-release
git checkout tags/HDP-2.6.4.91-3-tag
gradle
./gradlew installAll
cd ..

git clone https://github.com/swisscom-bigdata/ranger-release.git
cd ranger-release
git checkout HDP-2.6.4.91-3_knox120
mvn -DskipTests=true clean compile package install assembly:assembly
```

The plugin will be available under `ranger-release/target/ranger-0.7.0.2.6.3.0-SNAPSHOT-knox-plugin.tar.gz`.
