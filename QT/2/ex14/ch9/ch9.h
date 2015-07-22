#include <qimage.h>

#include "../nehewidget.h"

const int num = 50;
	
typedef struct
{
	int r, g, b;
	GLfloat dist, angle;
} stars;

class NeHeChapter9 : public NeHeWidget
{
private:

	bool twinkle;	

	stars star[num];
	
	GLfloat zoom;
	GLfloat tilt;
	GLfloat spin;
	
	GLuint texture[1];
	
	void loadGLTextures()
	{
		QImage t;
		QImage b;
		
		if ( !b.load( "../images/star.bmp" ) )
		{
			b = QImage( 16, 16, 32 );
			b.fill( Qt::green.rgb() );
		}
		
		t = QGLWidget::convertToGLFormat( b );
		glGenTextures( 1, &texture[0] );
		
		glBindTexture( GL_TEXTURE_2D, texture[0] );
    glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
		glTexImage2D( GL_TEXTURE_2D, 0, 3, t.width(), t.height(), 0, GL_RGBA, GL_UNSIGNED_BYTE, t.bits() );
	}
	
public:
	NeHeChapter9( QWidget *parent=0, char *name=0 ) : NeHeWidget( 50, parent, name )
	{
		twinkle = false;
		zoom = -15.0f;
		tilt = 90.f;
		spin = 0.0f;
	}
	
protected:
	void initializeGL()
	{
		loadGLTextures();
		
		glEnable(GL_TEXTURE_2D);                                
		glShadeModel(GL_SMOOTH);                                
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);                   
		glClearDepth(1.0f);                                     
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);      
		glBlendFunc(GL_SRC_ALPHA,GL_ONE);                       
		glEnable(GL_BLEND);     

		for( int loop=0; loop<num; loop++)                          
		{
			star[loop].angle=0.0f;    
			star[loop].dist=(float(loop)/num)*5.0f;         
			star[loop].r=rand()%256;                        
			star[loop].g=rand()%256;                        
			star[loop].b=rand()%256;                        
		}
	}
	
	void resizeGL( int width, int height )
	{
  	height = height?height:1;

		glViewport( 0, 0, (GLint)width, (GLint)height );

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);

		glColor4f(1.0f,1.0f,1.0f,0.5f);
    glBlendFunc(GL_SRC_ALPHA,GL_ONE);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	void paintGL()
	{ 
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBindTexture(GL_TEXTURE_2D, texture[0]);

		for ( int loop=0; loop<num; loop++ )
		{
			glLoadIdentity();
			glTranslatef(0.0f,0.0f,zoom);
			glRotatef(tilt,1.0f,0.0f,0.0f); 

			glRotatef(star[loop].angle,0.0f,1.0f,0.0f);
			glTranslatef(star[loop].dist,0.0f,0.0f);    

			glRotatef(-star[loop].angle,0.0f,1.0f,0.0f);
			glRotatef(-tilt,1.0f,0.0f,0.0f);    

			if (twinkle)
			{
				// Assign A Color Using Bytes
				glColor4ub(star[(num-loop)-1].r,star[(num-loop)-1].g,star[(num-loop)-1].b,255);
				glBegin(GL_QUADS);
        	glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,-1.0f, 0.0f);
        	glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,-1.0f, 0.0f);
        	glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f, 1.0f, 0.0f);
        	glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f, 1.0f, 0.0f);
				glEnd();
			}

			glRotatef(spin,0.0f,0.0f,1.0f);
			// Assign A Color Using Bytes
			glColor4ub(star[loop].r,star[loop].g,star[loop].b,255);
			glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,-1.0f, 0.0f);
			glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,-1.0f, 0.0f);
			glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f, 1.0f, 0.0f);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f, 1.0f, 0.0f);
			glEnd();  
		}      
	}
	
	void timeOut()
	{
		spin+=0.01f;

		for ( int loop=0; loop<num; loop++ )
		{
			star[loop].angle+=float(loop)/num;
			star[loop].dist-=0.01f;

			if (star[loop].dist<0.0f)
			{
				star[loop].dist+=5.0f;
				star[loop].r=rand()%256;
				star[loop].g=rand()%256;
				star[loop].b=rand()%256;
			}
		}		
		updateGL();
	}
	
	void keyPressEvent( QKeyEvent *e )
	{
		switch( e->key() )
		{
		case Qt::Key_T:
			twinkle = !twinkle;
		
			break;
			
		case Qt::Key_Up:
			tilt -= 0.01f;

			break;
			
		case Qt::Key_Down:
			tilt += 0.01f;

			break;
			
		case Qt::Key_PageDown:
			zoom -= 0.02f;
		
			break;
					
		case Qt::Key_PageUp:
			zoom += 0.02f;
			
		default:
			NeHeWidget::keyPressEvent( e );
		}
	}
};
