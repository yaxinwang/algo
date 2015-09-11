package yaxin.graph

import org.junit.Test
import org.junit.Assert;

class MovieSchedule {

	@Test
	void testGoodSchedule() {
		def schedule = trySchedule([new Customer("Tom", "AntMan", "XMan"), new Customer("John", "XMan", "AntMan")]);
		println schedule;
		Assert.assertNotNull(schedule);

		println '----------';

		schedule = trySchedule([new Customer("Tom", "AntMan", "XMan"), new Customer("John", "XMan", "AntMan"), new Customer("Sam", "AntMan", "SuperMan")]);
		println schedule;
		Assert.assertNotNull(schedule);
		println '----------';

		schedule = trySchedule([new Customer("Tom", "AntMan", "XMan"), new Customer("John", "XMan", "SuperMan"), new Customer("Sam", "SuperMan", "AntMan")]);
		println schedule;
		Assert.assertNull(schedule);
	}

	@Test
	void testNoSchedule() {
		def schedule = trySchedule([new Customer("Tom", "AntMan", "XMan"), new Customer("John", "XMan", "SuperMan"), new Customer("Sam", "SuperMan", "AntMan")]);
		println schedule;
		Assert.assertNull(schedule);
	}

	static List<List<String>> trySchedule(List<Customer> customers) {
		Customer customer = customers[0];

		//firstMovie then secondMovie
		List<String> saturday = [customer.firstMovie];
		List<String> sunday = [customer.secondMovie];

		def goodSchedule = trySchedule(customers, 1, [saturday, sunday]);
		if(goodSchedule != null) {
			return goodSchedule;
		}

		//secondMovie then firstMovie
		saturday = [customer.secondMovie];
		sunday = [customer.firstMovie];
		goodSchedule = trySchedule(customers, 1, [saturday, sunday]);
		if(goodSchedule != null) {
			return goodSchedule;
		}

		return null;
	}

	static List<List<String>> trySchedule(List<Customer> customers, int customerIndex, List<List<String>> schedule) {
		if(customerIndex >= customers.size()) {
			return schedule;
		}


		def saturday = schedule[0];
		def sunday = schedule[1];

		def customer = customers[customerIndex];

		println "$customer with existing schedule $schedule";

		if(saturday.contains(customer.firstMovie) && saturday.contains(customer.secondMovie)) {
			return null;
		}

		if(sunday.contains(customer.firstMovie) && sunday.contains(customer.secondMovie)) {
			return null;
		}

		if(saturday.contains(customer.firstMovie)) {
			if(sunday.contains(customer.secondMovie)) {
				//existing schedule fits
				return trySchedule(customers, customerIndex + 1, schedule);
			} else {
				sunday << customer.secondMovie;
				def s = trySchedule(customers, customerIndex + 1,schedule);
				if(s == null) {
					sunday.pop();
				}
				return s;
			}
		}

		if(saturday.contains(customer.secondMovie)) {
			if(sunday.contains(customer.firstMovie)) {
				//existing schedule fits
				return trySchedule(customers, customerIndex + 1,schedule);
			} else {
				sunday << customer.firstMovie;
				def s = trySchedule(customers, customerIndex + 1,schedule);
				if(s == null) {
					sunday.pop();
				}
				return s;
			}
		}

		if(sunday.contains(customer.firstMovie)) {
			if(saturday.contains(customer.secondMovie)) {
				//existing schedule fits
				return trySchedule(customers, customerIndex + 1,schedule);
			} else {
				saturday << customer.secondMovie;
				def s = trySchedule(customers, customerIndex + 1,schedule);
				if(s == null) {
					saturday.pop();
				}
				return s;
			}
		}

		if(sunday.contains(customer.secondMovie)) {
			if(saturday.contains(customer.firstMovie)) {
				//existing schedule fits
				return trySchedule(customers,customerIndex + 1, schedule);
			} else {
				saturday << customer.firstMovie;
				def s = trySchedule(customers, customerIndex + 1,schedule);
				if(s == null) {
					saturday.pop();
				}
				return s;
			}
		}

		//neither movie is scheduled yet

		//firstMovie then secondMovie
		saturday << [customer.firstMovie];
		sunday << [customer.secondMovie];

		def goodSchedule = trySchedule(customers, customerIndex + 1, schedule);
		if(goodSchedule != null) {
			return goodSchedule;
		}
		
		saturday.pop();
		sunday.pop();

		saturday << [customer.secondMovie];
		sunday << [customer.firstMovie];

		goodSchedule = trySchedule(customers, customerIndex + 1, schedule);
		if(goodSchedule != null) {
			return goodSchedule;
		}
		
		saturday.pop();
		sunday.pop();
		
		return null;
	}
	
	static void removeThisAndAfter(List<String> items, String item) {
		while(items.size() > 0) {
			if(items.pop() == item) {
				return;
			}
		}
		return;
	}

	static class Customer {
		String name;
		String firstMovie;
		String secondMovie;

		Customer(String name, String firstMovie, String secondMovie) {
			this.name = name;
			this.firstMovie = firstMovie;
			this.secondMovie = secondMovie;
		}

		@Override
		public String toString() {
			return "Customer [name=" + name + ", firstMovie=" + firstMovie + ", secondMovie=" + secondMovie + "]";
		}


	}

}

