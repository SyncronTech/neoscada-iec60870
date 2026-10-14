IEC-104 (IEC 60870) stuff from Eclipse NeoSCADA repository
==========================================================

This source has been extracted (with git filter-repo) from Eclipse NeoSCADA repository.
NeoSCADA itself uses Maven to build, but the whole thing is kind of complex beast with dozens
of modules and dependencies we don't need.

When writing this, the necessary modules are available in maven repositories, but
they are old versions (0.4.x) and we need fixes done for 0.5 (which has never been
released, there are only nightly builds in eclipse project servers). The NeoSCADA
project itself seems dead now.

Also, trying to build those locally with Maven seemed 'hard work' because of the size
of project.

So what this project does then: It takes necessary sources from neoscada git repository and adds 
gradle builds for those modules we need.

org.syncrontech.neoscada.protocol.iec60870.server.system project contains implementations for some
messages that were missing from the Eclipse library.

Building
--------

Requires JDK 21 or newer and Gradle 8.14: `gradle build`.

Related documentation
---------------------

- Beckhoff has some documents for [IEC-104 data structures][1].
- [Description and Analysis of IEC 104 Protocol][4], technical report by Petr Matoušek.
- Book "Practical Modern SCADA Protocols", chapters 7-9.
- [QTester IEC-104 client/master/scada][2]
- [FreyrSCADA tester][3]

[1]: https://infosys.beckhoff.com/english.php?content=../content/1033/tcplclibiec870_5_104/html/tcplclibiec870_5_104_telegrammstructure.htm&id
[2]: https://github.com/riclolsen/qtester104
[3]: https://github.com/FreyrSCADA/IEC-60870-5-104
[4]: https://www.fit.vut.cz/research/publication/11570/