# IDS-Meta-data-broker-extensions
The IDS-Meta-data-broker-extensions repository is meant to be the repository for all extensions of the
[metadata-broker-open-core](https://github.com/International-Data-Spaces-Association/metadata-broker-open-core) are functional
and have been deployed in some projects. The structure of the repository is as follows:
- **[broker-common](https://github.com/International-Data-Spaces-Association/IDS-Meta-data-broker-extensions/tree/main/index-common)** [DEPRECATED]: collects dependencies that extend the open-broker-common. In the meantime, most of its sources have been
  moved to other repositories. In a future version, it will be removed entirely from the repository.
- **[index-common](https://github.com/International-Data-Spaces-Association/IDS-Meta-data-broker-extensions/tree/main/index-common)**: 
contains sources related to specific versions of the front-end and the persistence that are core dependencies of
  any closed source version of the metadata Broker
- **[elastic search-indexing-provider](https://github.com/International-Data-Spaces-Association/IDS-Meta-data-broker-extensions/tree/main/index-common)**: contains several implementations of the indexing interface of the open-core Broker that
  provide an indexing of particular aspects of the Fuseki used for persistence.

## Building
Before you build the project, please be aware of the fact that the versions of the parent artifact is decouple from the 
individual modules. The basic idea is that the version of the main artifact changes if any of the sub module versions changes.
The versions are defined in the parent [pom](https://github.com/International-Data-Spaces-Association/IDS-Meta-data-broker-extensions/blob/main/pom.xml). 
To build and/or install the artifacts simply use
````shell
mvn clean package 
````
and or 
````shell
mvn clean install
````