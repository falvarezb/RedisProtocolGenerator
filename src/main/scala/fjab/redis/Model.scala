package fjab.redis

case class Seat(coachNumber: Int, seatNumber: Int)
case class Passenger(firstName: String, lastName: String, seat: Seat)
case class Ticket(date: String, price: String, passengers: List[Passenger])