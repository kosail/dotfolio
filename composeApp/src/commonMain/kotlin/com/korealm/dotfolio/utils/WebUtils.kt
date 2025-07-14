package com.korealm.dotfolio.utils

/*
* The CV.pdf file download function only happens on web target (it does not make sense to build it for desktop, as my intention is to build this portfolio for web, to run on a hosting... not to be run on local
* Still, I've to make them expect/actual functions to be able to keep running the project on local to use hot-reload.
*
* I'll just make the desktop actual implementations empty
*/

expect fun openInNewTab(url: String)