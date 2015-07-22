#ifndef NEHEWIDGET_H
#define NEHEWIDGET_H

#include <qgl.h>

class QTimer;

class NeHeWidget : public QGLWidget
{
	Q_OBJECT
	
public:
	NeHeWidget( int timerInterval=0, QWidget *parent=0, char *name=0 );

protected:
	virtual void initializeGL() = 0;
	virtual void resizeGL( int width, int height ) = 0;
	virtual void paintGL() = 0;
	
	virtual void keyPressEvent( QKeyEvent *e );
	
	virtual void timeOut();
	
protected slots:
	virtual void timeOutSlot();
	
private:
	QTimer *m_timer;
};

#endif
