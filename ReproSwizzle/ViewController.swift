//
//  ViewController.swift
//  ReproSwizzle
//
//  Created by Julius Skripkauskas on 11/25/20.
//  Copyright © 2020 Julius Skripkauskas. All rights reserved.
//

import UIKit
import shared
import Firebase

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.

        
        ApiImpl().getData { result, error in
            print("Result \(result)")
        }
    }
    
    


}

