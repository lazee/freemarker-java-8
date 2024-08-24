In order to release this package you need a Sonatype account connected to the project. Contact Jakob for help on this.

You also need to configure a special settings.xml when releasing. Also ask Jakob about this.

Release package:

* Release with jdk 8!
* mvn release:prepare
* mvn release:perform
* Go to https://oss.sonatype.org/ and log in.
* Click on staging repositories and find your release.
* Click on the release and hit the close button, then the release button (when ready. Might take a while).

The release will be available in the Central Repo within a day.

More info:

http://central.sonatype.org/pages/releasing-the-deployment.html

## About GPG Signing

* You must have gpg installed on the PC where you deploy. On mac `brew install gpg`
* Make sure to generate a signing key `gpg --gen-key`

Remember to upload your new gpg key to a key server:

https://keys.openpgp.org/about/usage

For example: 

* gpg --list-secret-keys --keyid-format LONG
* gpg --keyserver hkp://keyserver.ubuntu.com --send-keys <name of your key>