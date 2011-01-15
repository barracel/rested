
ReSTed editor
=============
Basic reStructuredText markup editor for eclipse

Motivation
~~~~~~~~~~
Not sure why I haven't been able to found any reStructuredText editor for eclipse (shame on you pydev guys :P)
So I decided to implement it myself.

Status
~~~~~~
The current version is ReSTEd_1.0.0.alpha and only has a very crude non configurable syntax higlight for:

- Sections
- Emphasis
- Interpreted
- InlineLiteral
- Links

Has been tested only in Ubuntu lucid + eclipse helios
  
How to install
~~~~~~~~~~~~~~
Right now the only way to install it is by compiling the plugin and
droping the jar file in your plugin/dropin folder.
I still need to improve this...  
  
TODO
~~~~~
There many things to do. But a very basic list could be:

- Fix some bugs that the syntax parser already has (mostly with sections partitions)
- Chose better color scheme (Right now has the classic RGB coder palette...)
- Add more syntax elements to the parser
- Update site to ease installation and updates
- Add block support and auto indentation
- Html/Pdf preview
- Tables support (I envy you! emacs users!)
- Spell checker (you know you need it!)
- Choose a lincense
- Improve TODO list...
