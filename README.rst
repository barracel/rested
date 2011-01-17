
ReSTed editor
=============
Basic *reStructuredText* markup editor for eclipse

Motivation
~~~~~~~~~~
Not sure why I haven't been able to found any reStructuredText editor for eclipse (shame on you pydev guys :P)
So I decided to implement it myself.

Status
~~~~~~
The current version is *ReSTEd_1.0.0.alpha1* and only has a very crude non configurable syntax higlight for:

- Sections
- Emphasis
- Interpreted
- InlineLiteral
- Links

Has been tested only in Ubuntu lucid + eclipse helios

Screenshots
~~~~~~~~~~~

.. image:: http://www.hackrastinator.com/rested/img/tn_screenshot1.png
   :alt: Screenshot of rested eclipse plugin
   :target: http://www.hackrastinator.com/rested/img/screenshot1.png 
   
  
How to install
~~~~~~~~~~~~~~

An update site have been created in http://www.hackrastinator.com/rested/update 

The plugin is not signed so you will get a ugly warning about installing
unsinged content... 
 
Also you can always compile the plugin and
copy the jar file in your plugin/dropin folder.
  
  
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
- Fix the Unsinged content warning in the update site.. (Any kind sould with a
  trusted certificate to sign it for me?!?!)
- Improve TODO list...
