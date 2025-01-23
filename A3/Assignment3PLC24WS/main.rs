mod client; // includes a module
mod image; 
mod complex; 
mod mandelbrot; 
use std::fmt;
//use std::ops::{Add, Mul};

//use crate::complex::Complex;
use crate::image::Image;


// cargo clean && cargo build && cargo run -- 10 20 30

fn main() {

    println!("Hello, world! from the rust");
    

    
    let width: usize;
    let height: usize;
    let max_iterations: usize;
    
    
    if let Ok((w, h, m)) = client::parse_args() {
        width = w as usize;
        height = h as usize;
        max_iterations = m as usize;
    } else {
        eprintln!("Error parsing arguments");
        std::process::exit(1);
    }

    vector_test(width, height, max_iterations);
    
    /*
    println!("Generating Mandelbrot for {}x{} image (max_iterations: {})", width, height, max_iterations);



    let image: Image = mandelbrot::generate_image(width, height, max_iterations);// and save the result to an image

    // call the get_mandelbrot_pixels() method on the image struct and save the result in mandelbrot_pixel_count

    let mandelbrot_pixel_count: usize = image.get_mandelbrot_pixels();

    // if the above line worked you should be able to uncomment this line
    println!("Pixels in the set: {}", mandelbrot_pixel_count);
    
    // uncomment and call after you implement the mandelbrot functions, and handle the possible error
    client::save_to_file(&image, "mandelbrot.ppm");
    */
}

// Define the Person structure
struct Person {
    name: String,
    age: u8,
}

impl fmt::Display for Person {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{} {}", self.name, self.age)
    }
}


fn vector_test(width: usize, height: usize, max_iterations: usize){

    println!("PErsons");
    // Create a vector of persons
    let persons = vec![
        Person {
            name: "Alice".to_string(),
            age: 30,
        },
        Person {
            name: "Bob".to_string(),
            age: 20,
        },
        Person {
            name: "Charlie".to_string(),
            age: 25,
        },
        Person {
            name: "Diana".to_string(),
            age: 40,
        },
    ];

    let mut newperson = persons.sort_by(|p| p.age);

    for p in newperson {
        println!("{}", p);
    }





}
