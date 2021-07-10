package vamsee.application.wordapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vamsee.application.wordapp.databinding.FragmentLetterListBinding


class LetterListFragment : Fragment(), OnClick {

    private var _binding: FragmentLetterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.letters
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu.findItem(R.id.switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout() {
        if (isLinearLayout) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter(this)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return;
        menuItem.icon = if (isLinearLayout)
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_baseline_grid_on_24)
        else
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_baseline_list_24)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onLetterClick(item: Char, view: View) {
//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra(WordListFragment.LETTER, item)
//        this.startActivity(intent)

        val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(item.toString())
        view.findNavController().navigate(action)
    }
}

