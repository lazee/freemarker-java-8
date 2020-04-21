In order to release this package you need a Sonatype account connected to the project. Contact Jakob for help on this.

You also need to configure a special settings.xml when releasing. Also ask Jakob about this.

Release package:

* Release with jdk 8!
* mvn release:prepare
* mvn release:perform
* Go to https://oss.sonatype.org/ and log in.
* Click on staging repositories and find your release.
* Click on the release and hit the close button, then the release button.

The release will be available in the Central Repo within a day.

More info:

http://central.sonatype.org/pages/releasing-the-deployment.html
