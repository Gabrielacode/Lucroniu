package com.allstars.lucroniu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allstars.lucroniu.databinding.ActivitySecondBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
    lateinit var binding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fab.setOnClickListener {
            Snackbar.make(binding.main,"Hello ",Snackbar.LENGTH_SHORT).show()
        }

    }
}
//Today we will be learning about Coordinator Layout and other design support librarys
/*
* It is used to handle many behaviours and animations in both layout and scroll behaviour in its child
* s views
* We use behaviours to control the relationship with views with other views
* There are two main behaviours
* Layout and Scroll Behaviour
* Layout Behaviours are use to move or transition views when action happens
* Scroll Behviours ->There are based on the behaviours of scroll gestures
* For Behaviours we will need two things
* The child and the dependency
* The child is what the behaviour will be applied to
* The dependcy is what will trigger the behaviour
* //We will simple layout behaviour like the fab and the snackbar
* //Wecan also define our own custom behaviours
* As the fab button  has default behaviour that detects snackbars
*To position a view  we use layout gravity , anchor or anchor gravity
* Now we have seen an example we will talk about AppBar LAYOUT
* This is also a new design support library that works with the coordinator layout
* When we use the app bar layout we can describe a layout that the toolbar and other views can stay in
* The appbar layout defines an xml attribute that any dependcy view will use
* and the child of the app bar layout can decide on what haps when the behaviours is called
* The coordinator layout must be the parent of the app bar layout for all this to work
*In the xml we define the child as the nested scroll view and the dependecy as the appbar layout
* For us to describe changes to views in the app bar layout we define scroll flags
* We have to put for any scroll effects to take place
*The flags as follows
*Scroll -> it will react to any scroll events from the dependency  for accepting scroll events it is important reactimg to any scroll events
* On enter always -> it will react any scrolling up event or downward scroll event making the view visible
* enterAlwaysCollapsed ->Will always enter the collapsed mode if min Height is set during any downward scroll until the end of the scroll or top of the screen
* exituntilCollapsed ->The app bar layout view is scrolled until finished b4 the content is scrolled
* snap -> If the view is scrolled less than 50 % then it will return to its original size else it disappears
*
* Today we will be learning about Collapsing ToolBar Layout which is normally used in the AppBAR Layout to create a parallax
*We have a collapsing title  which beomes bigger when the text
* The scroll flags of this layout are normally set to exitUntilCollapsed |scroll
*
* */