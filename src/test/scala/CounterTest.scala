import org.reactormonk.Counter
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class CounterSpec extends FunSpec with ShouldMatchers {
  val simple = Counter(Map("foo" -> 1, "bar" -> 2))

  it("should convert a map and back") {
    simple.toMap should equal(Map("foo" -> 1, "bar" -> 2))
  }
  it("should add a key") {
    (simple + "foo").toMap should equal(Map("foo" -> 2, "bar" -> 2))
  }
  it("should subtract a key (and keep it if 0)") {
    (simple - "foo").toMap should equal(Map("foo" -> 0, "bar" -> 2))
  }
  it("should also allow full changing") {
    (simple.change("foo", 2)).toMap should equal(Map("foo" -> 3, "bar" -> 2))
  }
  // TODO: Anyone got an idea how to test this?
  // it("should disallow type changes") {
  //   (simple.change("foo", 0.5))
  // }
  it("should also allow full changing for negative values") {
    (simple.change("foo", -2)).toMap should equal(Map("foo" -> -1, "bar" -> 2))
  }
  it("should add together two counters") {
    (simple ++ Counter(Map("bar" -> -1))).toMap should equal(Map("foo" -> 1, "bar" -> 1))
  }
  it("should multiply") {
    (simple * 2).toMap should equal(Map("foo" -> 2, "bar" -> 4))
  }
  it("should return an Option containing the element") {
    simple.get("foo") should equal (Some(1))
  }
  it("should return the element or 0") {
    simple("foo") should equal(1)
    simple("baz") should equal(0)
  }
  it("should return a list") {
    simple.toList should equal(List(("foo", 1), ("bar", 2)))
  }
  it("should return a sequence") {
    simple.toSeq should equal(Seq(("foo", 1), ("bar", 2)))
  }
  it("should test equality correctly") {
    Counter(Map("foo" -> 1)) should equal(Counter(Map("foo" -> 1)))
  }
  it("should return an empty counter") {
    simple.empty should equal(Counter(simple.toMap.empty))
  }
  it("should return the number of tokens") {
    simple.size should equal(2)
  }
  it("should count the element of an Iterable when constructed that way") {
    Counter(Seq("bar", "foo", "bar")) should equal(simple)
  }
  it("should map values") {
    Counter(Seq("bar", "foo", "bar", "foo", "bar")).mapValues(_ - 1) should equal(simple)
  }
  it ("should return the element with the highest count") {
    Counter(Seq("bar", "foo", "bar")).max should equal("bar")
  }
  it ("should return the element with the lowest count") {
    Counter(Seq("bar", "foo", "bar")).min should equal("foo")
  }

  val floaty = Counter(Map("foo" -> 1.5, "bar" -> 2.5))

  it("should add to a floaty counter") {
    (floaty + "foo").toMap should equal(Map("foo" -> 2.5, "bar" -> 2.5))
  }
  it("should combine a floaty and an int counter") {
    (floaty ++ simple.toCounter[Double]).toMap should equal(Map("foo" -> 2.5, "bar" -> 4.5))
  }
  it("should multiply floaty") {
    // albeit you should not == floats
    (floaty * 2).toMap should equal(Map("foo" -> 3.0, "bar" -> 5.0))
  }
  it("should sum values") {
    simple.sum should equal(3)
    floaty.sum should equal(4.0)
  }
  it("should normalize") {
    Counter(Map("foo" -> 1, "bar" -> 3)).normalize should equal(Counter(Map("foo" -> 0.25, "bar" -> 0.75)))
  }
}
