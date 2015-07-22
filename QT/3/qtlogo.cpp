
#include "qtlogo.h"

void Patch::draw() const
{
    glPushMatrix();
    qMultMatrix(mat);
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, faceColor);

    const GLushort *indices = geom->faces.constData();
    glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_SHORT, indices + start);
    glPopMatrix();
}

void QtLogo::buildGeometry(int divisions, qreal scale)
{
    qreal cw = cross_width * scale;
    qreal bt = bar_thickness * scale;
    qreal ld = logo_depth * scale;
    qreal th = tee_height *scale;

    RectPrism cross(geom, cw, bt, ld);
    RectPrism stem(geom, bt, th, ld);

    QVector3D z(0.0, 0.0, 1.0);
    cross.rotate(45.0, z);
    stem.rotate(45.0, z);

    qreal stem_downshift = (th + bt) / 2.0;
    stem.translate(QVector3D(0.0, -stem_downshift, 0.0));

    RectTorus body(geom, 0.20, 0.30, 0.1, divisions);

    parts << stem.parts << cross.parts << body.parts;

    geom->finalize();
}

void QtLogo::draw() const
{
    geom->loadArrays();

    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_NORMAL_ARRAY);

    for (int i = 0; i < parts.count(); ++i)
        parts[i]->draw();

    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_NORMAL_ARRAY);
}
