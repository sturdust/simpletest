package diffprocessor;

/**
 * Created by user on 17.04.2016.
 */

import java.util.ArrayList;
import java.util.List;

public class Processor {
    public Processor(long limit) {
        SortedLimitedList list = new SortedLimitedList((int)limit);
        list.limit = (int)limit;// TODO: initialize.
    }

    public void doProcess(SortedLimitedList<Double> mustBeEqualTo, SortedLimitedList<Double> expectedOutput) {

        if(mustBeEqualTo.equals(expectedOutput))
            return;
        SortedLimitedList.Entry<Double> elementMust = mustBeEqualTo.getFirst();
        SortedLimitedList.Entry<Double> elementExpected = expectedOutput.getFirst();
        switch (expectedOutput.getCount()){
            case 0:
                int n0 = mustBeEqualTo.getCount();
                for (int i = 0; i < n0; i++) {
                    mustBeEqualTo.remove(mustBeEqualTo.getFirst());
                }break;
            case 1:
                int n1 = mustBeEqualTo.getCount();
                for (int i = 0; i < n1; i++) {
                    mustBeEqualTo.remove(mustBeEqualTo.getFirst());
                }
                mustBeEqualTo.addFirst(expectedOutput.getFirst().getValue());
                break ;
            default:
                int nd = expectedOutput.getCount();
                for (int i = 0; i < nd; i++) {
                    if (elementMust.getValue().equals(elementExpected.getValue()) == true) {
                        if (elementExpected.getNext() != null) {
                            elementExpected = elementExpected.getNext();
                        }
                        else {
                            List<SortedLimitedList.Entry<Double>> listHelp = new ArrayList<SortedLimitedList.Entry<Double>>();
                            int help = mustBeEqualTo.getCount() - i-1;

                            while(elementMust.getNext() != null){
                                elementMust = elementMust.getNext();
                                listHelp.add(elementMust);
                            }
                            for (int k = 0; k <help; k++) {
                                mustBeEqualTo.remove(listHelp.get(k));
                            }
                            break;
                        }

                        if (elementMust.getNext() != null) {
                            elementMust = elementMust.getNext();
                        }
                        else{
                            while (elementExpected != null){
                                mustBeEqualTo.addAfter(elementMust, elementExpected.getValue());
                                elementExpected = elementExpected.getNext();
                            }
                            break;
                        }
                        continue;
                    }
                    else{
                        if(i == 0){
                            if (elementMust.getNext() != null){
                                if (elementExpected.getValue() > elementMust.getNext().getValue()){
                                    int n2 = mustBeEqualTo.getCount();
                                    for (int i1 = 0; i1 < n2; i1++) {
                                        mustBeEqualTo.remove(mustBeEqualTo.getFirst());
                                    }
                                    while (elementExpected != null){
                                        mustBeEqualTo.addLast(elementExpected.getValue());
                                        elementExpected = elementExpected.getNext();
                                    }
                                    break;
                                }else{
                                    mustBeEqualTo.remove(elementMust);
                                    mustBeEqualTo.addFirst(elementExpected.getValue());
                                }
                            }else{
                                mustBeEqualTo.remove(elementMust);
                                mustBeEqualTo.addFirst(elementExpected.getValue());
                            }

                        }else {

                            if (elementMust.getNext() != null){
                                if (elementExpected.getValue() > elementMust.getNext().getValue()){
                                    int n2 = mustBeEqualTo.getCount();
                                    for (int i1 = i; i1 < n2; i1++) {
                                        mustBeEqualTo.remove(mustBeEqualTo.getLast());
                                    }
                                    while (elementExpected != null){
                                        mustBeEqualTo.addLast(elementExpected.getValue());
                                        elementExpected = elementExpected.getNext();
                                    }
                                    break;
                                }else{
                                    mustBeEqualTo.remove(elementMust);
                                    mustBeEqualTo.addAfter(elementMust.getPrevious(), elementExpected.getValue());
                                }
                            }else{
                                mustBeEqualTo.remove(elementMust);
                                mustBeEqualTo.addAfter(elementMust.getPrevious(), elementExpected.getValue());
                            }

                        }
                        if (elementExpected.getNext() != null) {
                            elementExpected = elementExpected.getNext();
                        }
                        else {
                            List<SortedLimitedList.Entry<Double>> listHelp1 = new ArrayList<SortedLimitedList.Entry<Double>>();
                            int help1 = mustBeEqualTo.getCount() - i-1;

                            while(elementMust.getNext() != null){
                                elementMust = elementMust.getNext();
                                listHelp1.add(elementMust);
                            }
                            for (int k = 0; k <help1; k++) {
                                mustBeEqualTo.remove(listHelp1.get(k));
                            }
                            break;
                        }

                        if (elementMust.getNext() != null) {
                            elementMust = elementMust.getNext();
                        }
                        else{
                            while (elementExpected != null){
                                mustBeEqualTo.addAfter(elementMust, elementExpected.getValue());
                                elementExpected = elementExpected.getNext();
                            }
                            break;
                        }
                        continue;
                    }
                }

        }


        // TODO: make "mustBeEqualTo" list equal to "expectedOutput".
        // 0. Processor will be created once and then will be used billion times.
        // 1. Use methods: AddFirst, AddLast, AddBefore, AddAfter, Remove to modify list.
        // 2. Do not change expectedOutput list.
        // 3. At any time number of elements in list could not exceed the "Limit".
        // 4. "Limit" will be passed into Processor's constructor. All "mustBeEqualTo" and "expectedOutput" lists will have the same "Limit" value.
        // 5. At any time list elements must be in non-descending order.
        // 6. Implementation must perform minimal possible number of actions (AddFirst, AddLast, AddBefore, AddAfter, Remove).
        // 7. Implementation must be fast and do not allocate excess memory.
    }
}