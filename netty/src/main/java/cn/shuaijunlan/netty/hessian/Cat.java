package cn.shuaijunlan.netty.hessian;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 7:55 PM 10/29/18.
 */
@Getter
@Setter
public class Cat implements Serializable {
    private String name;
    private String sex;
    private int age;

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if (obj instanceof Cat){
            Cat temp = (Cat) obj;
            return this.age == temp.age && this.name.equals(temp.name) && this.sex.equals(temp.sex);
        }
        return false;
    }
}
