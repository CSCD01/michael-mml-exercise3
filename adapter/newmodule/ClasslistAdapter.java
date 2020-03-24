package adapter.newmodule;

import adapter.oldmodule.Classlist;
import adapter.oldmodule.Student;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Client expects to use Classlist from newmodule (i.e. they have a classlist
 * with some features). We have a Classlist from oldmodule that has the same
 * features, but different interface. We also have a ClasslistAdapter (hidden)
 * that can translate features from newmodule to oldmodule. Therefore, client
 * can use Classlist from newmodule, which (secretly) calls ClasslistAdapter to
 * connect with the Classlist from oldmodule.
 *
 * <p>
 * Target = Classlist (newmodule) Adapter = ClasslistAdapter Adaptee = Classlist
 * (oldmodule)
 */
public class ClasslistAdapter implements adapter.newmodule.Classlist {
  private Classlist classlist;

  public ClasslistAdapter(Classlist classlist) {
    this.classlist = classlist;
  }

  @Override
  public String getSession() {
    return this.classlist.getSession();
  }

  @Override
  public String getCourseNumber() {
    return this.classlist.getCourseName();
  }

  @Override
  public String getCourseName() {
    return this.classlist.getCourseName();
  }

  @Override
  public void addStudent(Student student) {
    this.classlist.addStudent(student);
  }

  @Override
  public Iterable<Student> getStudents() {
    Enumeration<Student> students = this.classlist.getStudents(); // get enumeration

    // create a new Iterable
    Iterable<Student> studentIterable = new Iterable<Student>() {
      // an Iterable is defined as having a public function called iterator()
      // that returns an Iterator
      public Iterator<Student> iterator() {
        // an Iterator must implemented the functions hasNext() and next()
        Iterator<Student> studentIterator = new Iterator<Student>() {
          @Override
          public boolean hasNext() {
            return students.hasMoreElements();
          }

          @Override
          public Student next() {
            return students.nextElement();
          }
        };

        // return a new Iterator
        return studentIterator;
      }
    };

    // return a new Iterable
    return studentIterable;
  }
}
