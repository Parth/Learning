#include "nehewidget.h"

#include <qapplication.h>
#include <qtimer.h>

NeHeWidget::NeHeWidget( int timerInterval, QWidget *parent, char *name ) : QGLWidget( parent, name )
{
	if( timerInterval == 0 )
		m_timer = 0;
	else
	{
		m_timer = new QTimer( this );
		connect( m_timer, SIGNAL(timeout()), this, SLOT(timeOutSlot()) );
		m_timer->start( timerInterval );
	}
}

void NeHeWidget::keyPressEvent( QKeyEvent *e )
{
	switch( e->key() )
	{
	case Qt::Key_Escape:
		close();
	}
}

void NeHeWidget::timeOut()
{
}

void NeHeWidget::timeOutSlot()
{
	timeOut();
}
