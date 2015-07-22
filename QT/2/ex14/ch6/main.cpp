#include <qapplication.h>

#include "ch6.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter6();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
