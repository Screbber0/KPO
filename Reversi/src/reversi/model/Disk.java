package reversi.model;

/**
 * Класс описывающий фишку
 */
public class Disk {

    /**
     * Цвет фишки
     */
    private Color color;

    /**
     * Конструктор, устанавливающий фишке цвет
     * @param color цвет фишки
     */
    public Disk(Color color) {
        this.color = color;
    }

    /**
     * Возврат цвета
     * @return цвет
     */
    public Color getColor() {
        return color;
    }

    /**
     * Установка цвета
     * @param color цвет
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
