(ns tests
  (:require [clojure.test :refer :all]
            [p001]
            [p002]
            [p003]
            [p004]
            [p005]
            [p006]
            [p007]
            [p008]
            [p009]
            [p010]
            [p011]
            [p012]
            [p013]
            [p014]
            [p015]
            [p016]
            [p017]
            [p018]
            [p019]
            [p020]
            [p021]
            [p022]
            [p023]
            [p024]
            [p025]
            [p026]
            [p027]
            [p028]
            [p029]
            [p030]
            [p031]
            [p032]
            [p033]
            [p034]
            [p035]
            [p036]
            [p037]
            [p038]
            [p039]
            [p040]
            [p041]
            [p042]
            [p043]
            [p044]
            [p045]
            [p046]
            [p047]
            [p048]
            [p049]
            [p050]
            [p051]
            [p052]
            [p053]
            [p054]
            [p055]
            [p056]
            [p057]
            [p058]
            [p059]
            [p060]
            [p061]
            [p062]
            [p063]
            [p064]
            [p065]
            [p066]
            [p067]
            [p068]
            [p069]
            [p070]
            [p071]
            [p072]
            [p073]
            [p074]
            [p075]
            [p076]
            [p077]
            [p078]
            [p079]
            [p080]
            [p081]
            [p082]
            [p083]
            [p084]
            [p085]
            [p086]
            [p087]
            [p088]
            [p089]
            [p090]
            [p091]
            [p092]
            [p093]
            [p094]
            [p095]
            [p096]
            [p097]
            [p098]
            [p099]
            [p100]))

(deftest test-all
  (testing "p001" (is (= 233168 (p001/solve))))
  (testing "p002" (is (= 4613732 (p002/solve))))
  (testing "p003" (is (= 6857 (p003/solve))))
  (testing "p004" (is (= 906609 (p004/solve))))
  (testing "p005" (is (= 232792560 (p005/solve))))
  (testing "p006" (is (= 25164150 (p006/solve))))
  (testing "p007" (is (= 104743 (p007/solve))))
  (testing "p008" (is (= 23514624000 (p008/solve))))
  (testing "p009" (is (= 31875000 (p009/solve))))
  (testing "p010" (is (= 142913828922 (p010/solve))))
  (testing "p011" (is (= 70600674 (p011/solve))))
  (testing "p012" (is (= 76576500 (p012/solve))))
  (testing "p013" (is (= 5537376230 (p013/solve))))
  (testing "p014" (is (= 837799 (p014/solve))))
  (testing "p015" (is (= 137846528820 (p015/solve))))
  (testing "p016" (is (= 1366 (p016/solve))))
  (testing "p017" (is (= 21124 (p017/solve))))
  (testing "p018" (is (= 1074 (p018/solve))))
  (testing "p019" (is (= 171 (p019/solve))))
  (testing "p020" (is (= 648 (p020/solve))))
  (testing "p021" (is (= 31626 (p021/solve))))
  (testing "p022" (is (= 871198282 (p022/solve))))
  (testing "p023" (is (= 4179871 (p023/solve))))
  (testing "p024" (is (= 2783915460 (p024/solve))))
  (testing "p025" (is (= 4782 (p025/solve))))
  (testing "p026" (is (= 983 (p026/solve))))
  (testing "p027" (is (= -59231 (p027/solve))))
  (testing "p028" (is (= 669171001 (p028/solve))))
  (testing "p029" (is (= 9183 (p029/solve))))
  (testing "p030" (is (= 443839 (p030/solve))))
  (testing "p031" (is (= 73682 (p031/solve))))
  (testing "p032" (is (= 45228 (p032/solve))))
  (testing "p033" (is (= 100 (p033/solve))))
  (testing "p034" (is (= 40730 (p034/solve))))
  (testing "p035" (is (= 55 (p035/solve))))
  (testing "p036" (is (= 872187 (p036/solve))))
  (testing "p037" (is (= 748317 (p037/solve))))
  (testing "p038" (is (= 932718654 (p038/solve))))
  (testing "p039" (is (= 840 (p039/solve))))
  (testing "p040" (is (= 210 (p040/solve))))
  (testing "p041" (is (= 7652413 (p041/solve))))
  (testing "p042" (is (= 162 (p042/solve))))
  (testing "p043" (is (= 16695334890 (p043/solve))))
  (testing "p044" (is (= 5482660 (p044/solve))))
  (testing "p045" (is (= 1533776805 (p045/solve))))
  (testing "p046" (is (= 5777 (p046/solve))))
  (testing "p047" (is (= 134043 (p047/solve))))
  (testing "p048" (is (= 9110846700 (p048/solve))))
  (testing "p049" (is (= 296962999629 (p049/solve))))
  (testing "p050" (is (= 997651 (p050/solve))))
  (testing "p051" (is (= 121313 (p051/solve))))
  (testing "p052" (is (= 142857 (p052/solve))))
  (testing "p053" (is (= 4075 (p053/solve))))
  (testing "p054" (is (= 376 (p054/solve))))
  (testing "p055" (is (= 249 (p055/solve))))
  (testing "p056" (is (= 972 (p056/solve))))
  (testing "p057" (is (= 153 (p057/solve))))
  (testing "p058" (is (= 26241 (p058/solve))))
  (testing "p059" (is (= 129448 (p059/solve))))
  (testing "p060" (is (= 26033 (p060/solve))))
  (testing "p061" (is (= 28684 (p061/solve))))
  (testing "p062" (is (= 127035954683 (p062/solve))))
  (testing "p063" (is (= 49 (p063/solve))))
  (testing "p064" (is (= 1322 (p064/solve))))
  (testing "p065" (is (= 272 (p065/solve))))
  (testing "p066" (is (= 661 (p066/solve))))
  (testing "p067" (is (= 7273 (p067/solve))))
  (testing "p068" (is (= 6531031914842725 (p068/solve))))
  (testing "p069" (is (= 510510 (p069/solve))))
  (testing "p070" (is (= 8319823 (p070/solve))))
  (testing "p071" (is (= 428570 (p071/solve))))
  (testing "p072" (is (= 303963552391 (p072/solve))))
  (testing "p073" (is (= 7295372 (p073/solve))))
  (testing "p074" (is (= 402 (p074/solve))))
  (testing "p075" (is (= 161667 (p075/solve))))
  (testing "p076" (is (= 190569291 (p076/solve))))
  (testing "p077" (is (= 71 (p077/solve))))
  (testing "p078" (is (= 55374 (p078/solve))))
  (testing "p079" (is (= 73162890 (p079/solve))))
  (testing "p080" (is (= 40886 (p080/solve))))
  (testing "p081" (is (= 427337 (p081/solve))))
  (testing "p082" (is (= 260324 (p082/solve))))
  (testing "p083" (is (= 425185 (p083/solve))))
  (testing "p084" (is (= 101524 (p084/solve))))
  (testing "p085" (is (= 2772 (p085/solve))))
  (testing "p086" (is (= 1818 (p086/solve))))
  (testing "p087" (is (= 1097343 (p087/solve))))
  (testing "p088" (is (= 7587457 (p088/solve))))
  (testing "p089" (is (= 743 (p089/solve))))
  (testing "p090" (is (= 1217 (p090/solve))))
  (testing "p091" (is (= 14234 (p091/solve))))
  (testing "p092" (is (= 8581146 (p092/solve))))
  (testing "p093" (is (= 1258 (p093/solve))))
  (testing "p094" (is (= 518408346 (p094/solve))))
  (testing "p095" (is (= 14316 (p095/solve))))
  (testing "p096" (is (= 24702 (p096/solve))))
  (testing "p097" (is (= 8739992577 (p097/solve))))
  (testing "p098" (is (= 18769 (p098/solve))))
  (testing "p099" (is (= 709 (p099/solve))))
  (testing "p100" (is (= 756872327473 (p100/solve)))))
