
ReSTed editor
=============
Basic *reStructuredText* markup editor for eclipse

Original Motivation
~~~~~~~~~~~~~~~~~~~
Circa 2010::
   Not sure why I haven't been able to found any reStructuredText editor for eclipse (shame on you pydev guys :P)
   So I decided to implement it myself.

Status
~~~~~~
**The project is mostly dead.** I started the project to scratch my own itch but now I
have http://resteditor.sourceforge.net/ to scratch it for me! There for all development of the project from my part
will cease (I really never enjoyed eclipse development) I left the code to dry out in the sun hoping someone can make some use of it

Version Alpha1
~~~~~~~~~~~~~~
The current version is *ReSTEd_1.0.0.alpha1* and only has a very crude non configurable syntax higlight for:

- Sections
- Emphasis
- Interpreted
- InlineLiteral
- Links

Has been tested only in Ubuntu lucid + Eclipse Helios

TODO
~~~~
There many things to do. But a very basic list could be:

- Fix some bugs that the syntax parser already has (mostly with sections partitions)
- Chose better color scheme (Right now has the classic RGB coder palette...)
- Add more syntax elements to the parser
- Add block support and auto indentation
- Html/Pdf preview
- Real time html preview like:
    -  http://www.youtube.com/watch?v=IaM5MgJqtdA
    -  http://blog.enthought.com/enthought-tool-suite/an-editor-for-restructured-text/
- Tables support (I envy you! emacs users!)
- Spell checker (you know you need it!)
- Fix the Unsinged content warning in the update site.. (Any kind sould with a
  trusted certificate to sign it for me?!?!)
- Improve TODO list...

Licensing
~~~~~~~~~

Copyright (c) Oscar Fernandez Barracel. All rights reserved.
The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html )
which can be found in the file LICENSE at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.
You must not remove this notice, or any other, from this software.