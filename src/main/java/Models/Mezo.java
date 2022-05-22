package Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A Mező tartalma
 */
@Getter
@Setter
@AllArgsConstructor
public class Mezo {
    /**
     * A mező színe.
     */
    private int szin;
    /**
     *  A bábú.
     *  0, ha üres a mező,
     *  1 ha fekete fútó,
     *  2 ha fehér futó van rajta.
     */
    private int babu;
}
