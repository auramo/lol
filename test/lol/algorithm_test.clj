(ns lol.algorithm-test
  (:use [clojure.test]
        [lol.algorithm]))

(deftest test-dimensions-of-item
  (is (= (dimensions-of-item {:id "1" :weight [5] :value 593})
         '(5))))

(deftest test-substract-from-limits
  (is (= (substract-from-limits {:weight [9]} '(4))
         '(-5)))
  (is (= (substract-from-limits {:weight [11 4]} '(20 19))
         '(9 15)))
  (is (= (substract-from-limits {:weight [5 3 12]} '(20 34 19))
         '(15 31 7))))

(deftest test-fill-knapsack
  (let [items [{:weight [642, 452]},
               {:weight [62, 152]}]
        limits [700 500]]
    (is (= [{:weight [642, 452]}] (fill-knapsack items limits))))
  (let [items [{:weight [642, 452]},
               {:weight [62, 152]}]
        limits [600 500]]
    (is (= [{:weight [62, 152]}] (fill-knapsack items limits))))
  (let [items [{:weight [642, 452]},
               {:weight [28, 152]},
               {:weight [62, 152]},
               { :weight [621, 152]}]
        limits [500 500]]
    (is (= [{:weight [62, 152]},
            {:weight [28, 152]},] (fill-knapsack items limits)))))

(deftest test-items-to-id-list
  (let [items [{:id "3"} {:id "12"} {:id "57"} {:id "1"}]]
    (is (= ["3" "12", "57" "1"] (items-to-id-list items)))))

(deftest test-relative-value
  (is (= 2/15 (relative-value {:weight [23 44 8] :value 10})))
  (is (= 93/10 (relative-value {:id "3" :weight [5 5] :value 93})))
  (is (= 53/10 (relative-value {:id "2" :weight [5 5] :value 53})))
  (is (= 3/10 (relative-value {:id "1" :weight [5 5] :value 3}))))

(deftest test-sort-by-relative-value
  (let [item1 {:id "1" :weight [5 5] :value 3}
        item2 {:id "2" :weight [5 5] :value 53}
        item3 {:id "3" :weight [5 5] :value 93}
        items [item1 item2 item3]]
    (is (= [item3 item2 item1] (sort-by-relative-value items)))))