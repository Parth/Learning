#include <qapplication.h>

#include "ch12.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter12();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
