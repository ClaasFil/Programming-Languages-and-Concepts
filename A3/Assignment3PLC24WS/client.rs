use std::fs;
use std::env;
use std::num::ParseIntError;
use crate::image::Image;
// you may need use std::env for parsing arguments
// use std::num::ParseIntError;

//use crate::image::Image; // use from another module

// uncomment and implement: 
pub fn parse_args() -> Result<(i32, i32, i32), ParseIntError> {
    // Collect the command-line arguments
    let args: Vec<String> = env::args().collect();

    // Ensure we have exactly the expected number of arguments
    if args.len() != 4 {
        eprintln!("Usage: {} <width> <height> <max_iterations>", args[0]);
        std::process::exit(1);
    }

    

    // Parse arguments to `usize`
    let arg1 = args[1].parse::<i32>()?;
    let arg2 = args[2].parse::<i32>()?;
    let arg3 = args[3].parse::<i32>()?;


    // Return the parsed arguments as a tuple
    Ok((arg1, arg2, arg3))
}


pub fn save_to_file(image: &Image, filename: &str) {
    let mut s = String::new();
    s.push_str(&format!("P3\n{} {}\n255\n", image.width, image.height));
    
    for y in 0..image.height {
        for x in 0..image.width {
            let pixel = image.get(x, y).unwrap();
            s.push_str(&format!("{}\n", pixel));
        }
    }

    fs::write(filename, s).expect("Error writing to disk!");
}
