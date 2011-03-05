(ns lol.tabu-test
  (:use [clojure.test]
        [lol.tabu]))

(deftest test-item-on
  (let [item {:id "4"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-on mapsack item)
           expected))))

(deftest test-item-on-for
  (let [item {:id "4"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        expected {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-on-for item) mapsack)
           expected))))
  
(deftest test-item-off
  (let [item {:id "2"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= (item-off mapsack item)
           expected))))

(deftest test-item-off-for
  (let [item {:id "2"}
        mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"} "4" {:id "4"}}
        expected {"1" {:id "1"} "3" {:id "3"} "4" {:id "4"}}]
    (is (= ((item-off-for item) mapsack)
           expected))))

(deftest test-mapsack-value
  (let [mapsack {"1" {:value 1} "2" {:value 2} "3" {:value 3} "4" {:value 4}}]
    (is (= (mapsack-value mapsack)
           10))))

(deftest test-objective
  (let [item1 {:id "1" :value 1}
        item5 {:id "5" :value 10}
        mapsack {(:id item1) item1 "2" {:value 2} "3" {:value 3} "4" {:value 4}}]    
    (is (= (objective mapsack (item-on-for item5))
           1))
    (is (= (objective mapsack (item-off-for item5))
           0))))

(deftest test-mapsack-weight
  (let [mapsack1 {"1" {:id "1" :weight [1 1]} "2" {:weight [2 2]} "3" {:weight [3 3]} "4" {:weight [4 4]}}
        mapsack2 {"1" {:id "1" :weight [1 1]} "2" {:weight [2 2]} "3" {:weight [3 3]}}]
    (is (= (mapsack-weight mapsack1)
           [10 10]))
    (is (= (mapsack-weight mapsack2)
           [6 6]))))

(deftest test-penalty
  (let [item1 {:id "1" :weight [1 1]}
        item5 {:id "5" :weight [5 5]}
        limits [12 12]
        mapsack {(:id item1) item1 "2" {:weight [2 2]} "3" {:weight [3 3]} "4" {:weight [4 4]}}]
    (is (= (penalty mapsack limits (item-on-for item5))
           1))
    (is (= (penalty mapsack limits (item-off-for item1))
           0))))

(deftest test-possible-item-on-moves
  (let [items [{:id "1"} {:id "2"}]
        mapsack {"4" {:id "4"}}
        moves (possible-item-on-moves items)]
    (is (= (map #(% mapsack) moves)
           [{"1" {:id "1"} "4" {:id "4"}}
            {"2" {:id "2"} "4" {:id "4"}}]))))

(deftest test-possible-item-off-moves
  (let [mapsack {"1" {:id "1"} "2" {:id "2"} "3" {:id "3"}}
        moves (possible-item-off-moves mapsack)]
    (is (= (map #(% mapsack) moves)
           [{"2" {:id "2"} "3" {:id "3"}}
            {"1" {:id "1"} "3" {:id "3"}}
            {"1" {:id "1"} "2" {:id "2"}}]))))


           
            