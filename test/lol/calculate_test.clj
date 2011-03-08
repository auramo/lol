(ns lol.calculate-test
  (:use [clojure.test]
    [lol.calculate]))

(deftest test-max-result
  (let [candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= candidate (max-result [] candidate))))
  (let [current [{:value 12} {:value 2} {:value 3}]
        candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= current (max-result current candidate))))
  (let [current [{:value 1} {:value 2} {:value 3}]
        candidate [{:value 5} {:value 2} {:value 3}]]
    (is (= candidate (max-result current candidate)))))
