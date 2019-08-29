import java.io.Serializable;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> implements Serializable {
    public boolean addTask(Task task) {
        return this.add(task);
    }

    public Task get(int itemId) {
        return super.get(itemId - 1);
    }

    public Task remove(int itemId) {
        return super.remove(itemId - 1);
    }

    public void markAsDone(int itemId) {
        super.get(itemId - 1).markAsDone();
    }

    public String toString() {
        StringBuffer listBuffer = new StringBuffer();
        int len = this.size();
        for(int i = 1; i <= len; i++) {
            listBuffer.append(i + "."
                    + this.get(i).toString()
                    + "\n");
        }
        return listBuffer.toString();
    }
}