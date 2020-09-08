package additional_task;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[][] inputArray = new Integer[][]{
                {1, null},
                {2, null},
                {3, 1},
                {4, 3},
                {5, null},
                {6, 3},
                {7, 2},
                {8, 5}
        };
        System.out.println(transformToHierarchyList(inputArray));
    }

    public static Collection<Objects> transformToHierarchyList(Integer[][] inputArray) {
        Map<Integer, Objects> idObjectsMap = new HashMap<>();
        for (Integer[] item : inputArray) {
            int id = item[0];
            Integer parentId = item[1];

            if (idObjectsMap.containsKey(id)) {
                throw new RuntimeException("Элемент с ID = " + id + " уже есть");
            }

            Objects newObjects = new Objects(id, parentId, Collections.emptyList());
            idObjectsMap.put(id, newObjects);

            if (parentId != null) {
                if (idObjectsMap.containsKey(parentId)) {
                    List<Objects> children = new ArrayList<>(idObjectsMap.get(parentId).getChildren());
                    children.add(newObjects);
                    idObjectsMap.get(parentId).setChildren(children);
                } else {
                    throw new RuntimeException("Ссылка на отсутствующий родительский элемент с ID = " + parentId);
                }
            }
        }
        return idObjectsMap.values();
    }
}
