package cn.shuaijunlan.netty.protostuff;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 9:58 AM 11/3/18.
 */
public final class Foo {
    public String name;
    public int id;

    public Foo(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (obj instanceof Foo){
           return ((Foo) obj).name.equals(this.name) && ((Foo) obj).id == this.id;
        }
        return false;
    }
}
