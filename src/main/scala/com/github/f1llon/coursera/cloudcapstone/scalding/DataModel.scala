package com.github.f1llon.coursera.cloudcapstone.scalding

/**
  * @author anton.v.filimonov@gmail.com
  * @since 1/22/16
  */
object DataModel {

  object AirlineOnTimeSchema extends Enumeration {
    val Year, Quarter, Month, DayofMonth, DayOfWeek, FlightDate, UniqueCarrier, AirlineID, Carrier, TailNum, FlightNum,
    Origin, OriginCityName, OriginState, OriginStateFips, OriginStateName, OriginWac, Dest, DestCityName, DestState,
    DestStateFips, DestStateName, DestWac, CRSDepTime, DepTime, DepDelay, DepDelayMinutes, DepDel15, DepartureDelayGroups,
    DepTimeBlk, TaxiOut, WheelsOff, WheelsOn, TaxiIn, CRSArrTime, ArrTime, ArrDelay, ArrDelayMinutes, ArrDel15,
    ArrivalDelayGroups, ArrTimeBlk, Cancelled, CancellationCode, Diverted, CRSElapsedTime, ActualElapsedTime, AirTime,
    Flights, Distance, DistanceGroup, CarrierDelay, WeatherDelay, NASDelay, SecurityDelay, LateAircraftDelay, SkipIt
    = Value
  }

}
